package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.impl.GeoEncodingServiceImpl;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLDataNotFoundException;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.BLValidationException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.vaildation.Validator;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.dao.DataAccessException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;

    private final Validator myValidator;

    private final TruckRepository truckRepository;

    private final WarehouseRepository warehouseRepository;

    private final TransferwarehouseRepository transferwarehouseRepository;

    private GeoEncodingServiceImpl geoEncodingServiceImpl = new GeoEncodingServiceImpl();

    private String generateTrackingId() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'Z')
                .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
                .build();
        return generator.generate(9);
    }

    private TruckEntity getNearestHop(RecipientEntity recipientEntity) throws BLValidationException {
        GeoCoordinateEntity geoCoordinateEntity = null;
        try {
            geoCoordinateEntity = geoEncodingServiceImpl.encodeAddress(recipientEntity);
        } catch (BLValidationException e) {
            throw new BLValidationException(e, e.getMessage());
        }
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(geoCoordinateEntity.getLon(), geoCoordinateEntity.getLat()));
        System.out.println(point);
        return truckRepository.findNearestHop(geoCoordinateEntity.getLat(), geoCoordinateEntity.getLon());
    }

    public List<HopEntity> calculateRoute(HopEntity hopA, HopEntity hopB, HopEntity warehouse) {
        log.info("in calculate route" );

        WarehouseEntity parentHopA = findParent((WarehouseEntity) warehouse, hopA);
        WarehouseEntity parentHopB = findParent((WarehouseEntity) warehouse, hopB);

        List<HopEntity> hops = new ArrayList<>();

        WarehouseEntity warehouseEntity = (WarehouseEntity) warehouse;
        for (WarehouseNextHopsEntity nextHop : warehouseEntity.getNextHops()) {

            if (parentHopA == parentHopB) {
                List<HopEntity> commonParent = new ArrayList<>();
                commonParent.add(parentHopA);
                return commonParent;
            } else {

                List<HopEntity> routeOfParents = calculateRoute(parentHopA, parentHopB, warehouse);

                routeOfParents.add(0, parentHopA);

                routeOfParents.add(parentHopB);

                return routeOfParents;
            }
        }
        return hops;
    }


    @Override
    public WarehouseEntity findParent(WarehouseEntity warehouse, HopEntity truck) {
        for (WarehouseNextHopsEntity nextHop : warehouse.getNextHops()) {
            if (nextHop.getHop().equals(truck)) {
                log.info("Code of Parent: "+warehouse.getCode());
                return warehouse;
            }
            if (nextHop.getHop() instanceof WarehouseEntity) {
                WarehouseEntity parentWarehouse = findParent((WarehouseEntity) nextHop.getHop(), truck);
                if (parentWarehouse != null) {
                    return parentWarehouse;
                }

            }
        }
        return null;
    }


    @Override
    public NewParcelInfoEntity submitParcel(ParcelEntity parcelEntity) throws BLException {
        log.info("in submitParcel");

        parcelEntity.setTrackingId(generateTrackingId());
        NewParcelInfoEntity newParcelInfoEntity = null;
        try {
            newParcelInfoEntity = saveParcelnReturnNewParcelInfo(parcelEntity, false);
        } catch (BLValidationException e) {
            log.error(e.getMessage());
            throw new BLException(e, e.getMessage());
        }
        newParcelInfoEntity.setTrackingId(parcelEntity.getTrackingId());
        return newParcelInfoEntity;
    }

    @Override
    public NewParcelInfoEntity transferParcel(String trackingId, ParcelEntity parcelEntity) throws BLException {

        try {
            if (parcelRepository.findByTrackingId(trackingId) != null) {
                throw new BLException(null, "A parcel with the specified trackingID is already in the system.");
            }
        }catch (DataAccessException e){
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }


        parcelEntity.setTrackingId(trackingId);
        NewParcelInfoEntity newParcelInfoEntity = null;
        try {
            newParcelInfoEntity = saveParcelnReturnNewParcelInfo(parcelEntity, true);
        } catch (BLValidationException e) {
            throw new BLException(e, e.getMessage());
        }
        newParcelInfoEntity.setTrackingId(trackingId);
        return newParcelInfoEntity;
    }

    public TrackingInformationEntity.StateEnumEntity getTrackingState(ParcelEntity parcelEntity, String code) {
        if (truckRepository.findByCode(code) != null) {
            return TrackingInformationEntity.StateEnumEntity.INTRUCKDELIVERY;
        } else if (warehouseRepository.findByCode(code) != null) {
            return TrackingInformationEntity.StateEnumEntity.INTRANSPORT;
        } else if (transferwarehouseRepository.findByCode(code) != null) {
            callLogisticsPartnerApi(transferwarehouseRepository.findByCode(code).getLogisticsPartnerUrl(), parcelEntity);
            return TrackingInformationEntity.StateEnumEntity.TRANSFERRED;
        }
        return null;
    }

    private void callLogisticsPartnerApi(String partnerUrl, ParcelEntity parcelEntity) {
        URI url = URI.create("https://" + partnerUrl + "/parcel/" + parcelEntity.getTrackingId());
        HttpClient client = HttpClient.newBuilder().build();
            String requestBody = parcelEntity.toString();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(url)
                .setHeader("Content-Type", "application/json")
                .build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        future
                .thenApply(HttpResponse::body)
                .thenAccept((response) -> {
                    log.info("response" + response);
                    log.info("logisticsPartnerApiCalled");

                })
                .join();
    }

    @Override
    public void reportParcelHop(String trackingId, String code) throws BLException {

        log.info("in report ParcelHop");

        ParcelEntity parcelEntity = null;
        HopArrivalEntity hopArrivalEntity = null;
        try {
            parcelEntity = parcelRepository.findByTrackingId(trackingId);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

        if (parcelEntity == null) {
            throw new BLDataNotFoundException(null, "Parcel does not exist with this tracking ID or hop with code not found");
        }
        for (HopArrivalEntity hop : parcelEntity.getFutureHops()) {
            if (hop.getCode().equals(code)) {
                hopArrivalEntity = hop;
            }
        }

        if (hopArrivalEntity == null) {
            throw new BLDataNotFoundException(null, "Parcel does not exist with this tracking ID or hop with code not found");
        }


        List<HopArrivalEntity> futureHops = parcelEntity.getFutureHops();
        List<HopArrivalEntity> visitedHops = parcelEntity.getVisitedHops();

        futureHops.remove(hopArrivalEntity);
        visitedHops.add(hopArrivalEntity);


        parcelEntity.setFutureHops(futureHops);
        parcelEntity.setVisitedHops(visitedHops);
        try {
            parcelEntity.setState(getTrackingState(parcelEntity, code));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

        try {
            parcelRepository.save(parcelEntity);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

    }

    private NewParcelInfoEntity saveParcelnReturnNewParcelInfo(ParcelEntity parcelEntity, Boolean transfer) throws BLValidationException {

        List<HopArrivalEntity> futureHops = new ArrayList<>();


        if(transfer){
            parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.TRANSFERRED);
        }else{
            parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);

        }
        TruckEntity truckEntityA = getNearestHop(parcelEntity.getRecipient());
        TruckEntity truckEntityB = getNearestHop(parcelEntity.getSender());

        WarehouseEntity warehouseEntity = warehouseRepository.findByLevel(0);

        List<HopEntity> route = calculateRoute(truckEntityA, truckEntityB, warehouseEntity);

        route.add(0, truckEntityA);
        route.add(truckEntityB);

        for (HopEntity hopOfRoute : route) {
            HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();
            hopArrivalEntity.setCode(hopOfRoute.getCode());
            hopArrivalEntity.setDescription(hopOfRoute.getDescription());
            hopArrivalEntity.setDateTime(OffsetDateTime.now());
            futureHops.add(hopArrivalEntity);
        }
        parcelEntity.setFutureHops(futureHops);

        try {
            myValidator.validate(parcelEntity);
            myValidator.validate(parcelEntity.getRecipient());
            myValidator.validate(parcelEntity.getSender());
        } catch (BLValidationException e) {
            log.error(e.getMessage());
            throw new BLValidationException(e, e.getMessage());
        }
        recipientRepository.save(parcelEntity.getRecipient());
        recipientRepository.save(parcelEntity.getSender());
        parcelRepository.save(parcelEntity);

        return new NewParcelInfoEntity();
    }

    @Override
    public void reportParcelDelivery(String trackingId) throws BLException {
        getParcelTrackingInformation(trackingId);
        try {
            parcelRepository.setStateToDelivered(trackingId);

        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

    }


    @Override
    public TrackingInformationEntity getParcelTrackingInformation(String trackingId) throws BLException {
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();
        newParcelInfoEntity.setTrackingId(trackingId);
        ParcelEntity parcelEntity;
        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity();

        try {
            parcelEntity = parcelRepository.findByTrackingId(trackingId);

        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

        if (parcelEntity == null) {
            log.error( "Parcel does not exist with this tracking ID.");
            throw new BLDataNotFoundException(null, "Parcel does not exist with this tracking ID.");
        }

        trackingInformationEntity.setState(parcelEntity.getState());
        trackingInformationEntity.setFutureHops(parcelEntity.getFutureHops());
        trackingInformationEntity.setVisitedHops(parcelEntity.getVisitedHops());

        return trackingInformationEntity;
    }
}
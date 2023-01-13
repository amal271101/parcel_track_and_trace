package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.impl.GeoEncodingServiceImpl;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.vaildation.Validator;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

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

    private TruckEntity getNearestHop(RecipientEntity recipientEntity) {
        GeoCoordinateEntity geoCoordinateEntity = geoEncodingServiceImpl.encodeAddress(recipientEntity);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(geoCoordinateEntity.getLon(), geoCoordinateEntity.getLat()));
        System.out.println(point);
        return truckRepository.findNearestHop(geoCoordinateEntity.getLat(), geoCoordinateEntity.getLon());
    }

    public List<HopEntity> calculateRoute(HopEntity hopA, HopEntity hopB, HopEntity warehouse) {
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
    public NewParcelInfoEntity submitParcel(ParcelEntity parcelEntity) {
        parcelEntity.setTrackingId(generateTrackingId());
        NewParcelInfoEntity newParcelInfoEntity = saveParcelnReturnNewParcelInfo(parcelEntity);
        newParcelInfoEntity.setTrackingId(parcelEntity.getTrackingId());
        return newParcelInfoEntity;
    }

    @Override
    public NewParcelInfoEntity transferParcel(String trackingId, ParcelEntity parcelEntity) {
        parcelEntity.setTrackingId(trackingId);
        NewParcelInfoEntity newParcelInfoEntity = saveParcelnReturnNewParcelInfo(parcelEntity);
        newParcelInfoEntity.setTrackingId(trackingId);
        return newParcelInfoEntity;
    }

    public TrackingInformationEntity.StateEnumEntity getTrackingState(ParcelEntity parcelEntity, String code) {
        if (truckRepository.findByCode(code) != null) {
            return TrackingInformationEntity.StateEnumEntity.INTRANSPORT;
        } else if (warehouseRepository.findByCode(code) != null) {
            return TrackingInformationEntity.StateEnumEntity.INTRANSPORT;
        } else if (transferwarehouseRepository.findByCode(code) != null) {
            callLogisticsPartnerApi(transferwarehouseRepository.findByCode(code).getLogisticsPartnerUrl(), parcelEntity.getTrackingId());
            return TrackingInformationEntity.StateEnumEntity.TRANSFERRED;
        }
        return null;
    }

    private void callLogisticsPartnerApi(String partnerUrl, String trackingId) {
        URI url = URI.create("https://" + partnerUrl + "/parcel/" + trackingId);
        HttpClient client = HttpClient.newBuilder().build();
        /**Call logistics partner API - TRANSFER – which has same contract as yours. p
         ---> POST Request ist leer **/
        String requestBody = null;
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(url)
                .setHeader("Content-Type", "application/json")
                .build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        future
                .thenApply(HttpResponse::body)
                .thenAccept((response) -> {

                    System.out.println(response);
                    System.out.println("logisticsPartnerApiCalled");
                })
                .join();
    }

    @Override
    public boolean reportParcelHop(String trackingId, String code) {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity == null) {
            return false;
        }
        List<HopArrivalEntity> futureHops = parcelEntity.getFutureHops();
        List<HopArrivalEntity> visitedHops = parcelEntity.getVisitedHops();

        HopArrivalEntity hopArrivalEntity = null;

        for (HopArrivalEntity hop : parcelEntity.getFutureHops()) {
            if (hop.getCode().equals(code)) {
                hopArrivalEntity = hop;
            }
        }

        if (hopArrivalEntity == null) {
            return false;
        }


        futureHops.remove(hopArrivalEntity);
        visitedHops.add(hopArrivalEntity);
        parcelEntity.setFutureHops(futureHops);
        parcelEntity.setVisitedHops(visitedHops);
        parcelEntity.setState(getTrackingState(parcelEntity, code));
        parcelRepository.save(parcelEntity);

        return true;
    }

    private NewParcelInfoEntity saveParcelnReturnNewParcelInfo(ParcelEntity parcelEntity) {
        HopArrivalEntity hop = new HopArrivalEntity();
        hop.setDateTime(OffsetDateTime.now());
        hop.setCode("ABAB790");
        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        List<HopArrivalEntity> futureHops = new ArrayList<>();

        visitedHops.add(hop);
        log.info(String.valueOf(visitedHops.get(0).getDateTime()));
        parcelEntity.setVisitedHops(visitedHops);
        parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);

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


        if(myValidator.validate(parcelEntity)){
            recipientRepository.save(parcelEntity.getRecipient());
            recipientRepository.save(parcelEntity.getSender());
            parcelRepository.save(parcelEntity);
        }else { return null;}
        return new NewParcelInfoEntity();
    }

    @Override
    public void reportParcelDelivery(String trackingId) {
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();
        newParcelInfoEntity.setTrackingId(trackingId);
        if (myValidator.validate(newParcelInfoEntity)) {
            parcelRepository.setStateToDelivered(trackingId);
        }
    }


    @Override
    public TrackingInformationEntity getParcelTrackingInformation(String trackingId) {
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();
        newParcelInfoEntity.setTrackingId(trackingId);
        ParcelEntity parcelEntity;
        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity();

        if (myValidator.validate(newParcelInfoEntity)) {
            parcelEntity = parcelRepository.findByTrackingId(trackingId);
            trackingInformationEntity.setState(parcelEntity.getState());
            trackingInformationEntity.setFutureHops(parcelEntity.getFutureHops());
            trackingInformationEntity.setVisitedHops(parcelEntity.getVisitedHops());
        }
        return trackingInformationEntity;
    }
}
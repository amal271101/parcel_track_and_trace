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

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;

    private final Validator myValidator;

    private final TruckRepository truckRepository;

    private final WarehouseRepository warehouseRepository;

    private GeoEncodingServiceImpl geoEncodingServiceImpl = new GeoEncodingServiceImpl();

    public String generateTrackingId() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'Z')
                .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
                .build();
        return generator.generate(9);
    }

    private TruckEntity getNearestHop(RecipientEntity recipientEntity) {
        GeoCoordinateEntity geoCoordinateEntity= geoEncodingServiceImpl.encodeAddress(recipientEntity);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(geoCoordinateEntity.getLon(), geoCoordinateEntity.getLat()));
        System.out.println(point);
        return truckRepository.findNearestHop(geoCoordinateEntity.getLat(),geoCoordinateEntity.getLon());
    }

    public List<HopEntity> calculateRoute(HopEntity hopA, HopEntity hopB, HopEntity warehouse) {
        WarehouseEntity parentHopA = findParent((WarehouseEntity) warehouse, hopA);
        WarehouseEntity parentHopB = findParent((WarehouseEntity) warehouse, hopB);

        List<HopEntity> hops = new ArrayList();

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

        /*if(myValidator.validate(parcelEntity)){
             return  new NewParcelInfoEntity();
          }*/

        /** PREDICT FUTURE HOPS HIER UND DAS OBERE ALLES LÖSCHEN**/

        TruckEntity truckEntityA= getNearestHop(parcelEntity.getRecipient());
        TruckEntity truckEntityB= getNearestHop(parcelEntity.getSender());
        WarehouseEntity warehouseEntity = warehouseRepository.findByLevel(0);

        List<HopEntity> route = calculateRoute(truckEntityA, truckEntityB, warehouseEntity);

        System.out.println("route"+Arrays.toString(route.toArray()));
        System.out.println(route.size());


        for (HopEntity hopOfRoute: route) {
            HopArrivalEntity hopArrivalEntity= new HopArrivalEntity();
            hopArrivalEntity.setCode(hopOfRoute.getCode());
            hopArrivalEntity.setDescription(hopOfRoute.getDescription());
            hopArrivalEntity.setDateTime(OffsetDateTime.now());
            futureHops.add(hopArrivalEntity);
        }
        parcelEntity.setFutureHops(futureHops);

        recipientRepository.save(parcelEntity.getRecipient());
        recipientRepository.save(parcelEntity.getSender());
        parcelRepository.save(parcelEntity);
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
package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TruckRepositoryTest {
    @Autowired
    TruckRepository truckRepository;

    @Test
    void saveAndDeleteTransferWareHouse() {


        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setDescription("This is also a  description");
        truckEntity.setCode("BLBA5");
        truckEntity.setHopType("hopytype");
        truckEntity.setLocationName("Floridsdorf");
        truckEntity.setProcessingDelayMins(23);
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[]{
                new Coordinate(-73.95, 40.78),
                new Coordinate(-73.95, 40.70),
                new Coordinate(-73.85, 40.70),
                new Coordinate(-73.85, 40.78),
                new Coordinate(-73.95, 40.78)
        };
        truckEntity.setRegionGeoJson(geometryFactory.createPolygon(geometryFactory.createLinearRing(coordinates)));

        truckEntity.setNumberPlate("34224");

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLon(45.56);
        geoCoordinateEntity.setLat(53.53);

        truckEntity.setLocationCoordinates(geoCoordinateEntity);


        truckRepository.save(truckEntity);

        assertEquals(truckRepository.findByCode(truckEntity.getCode()).getCode(), truckEntity.getCode());
        truckRepository.delete(truckRepository.findByCode(truckEntity.getCode()));
        assertNull(truckRepository.findByCode(truckEntity.getCode()));
    }


}
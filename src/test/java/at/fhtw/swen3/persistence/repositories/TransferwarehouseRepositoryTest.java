package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TransferwarehouseRepositoryTest {
    @Autowired
    TransferwarehouseRepository transferwarehouseRepository;
    @Test
    void saveAndDeleteTransferWareHouse() {


        TransferwarehouseEntity transferwarehouseEntity= new TransferwarehouseEntity();
        transferwarehouseEntity.setDescription("This is also a  description");
        transferwarehouseEntity.setCode("BLBA5");
        transferwarehouseEntity.setHopType("hopytype");
        transferwarehouseEntity.setLocationName("Floridsdorf");
        transferwarehouseEntity.setProcessingDelayMins(23);
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[] {
                new Coordinate(-73.95, 40.78),
                new Coordinate(-73.95, 40.70),
                new Coordinate(-73.85, 40.70),
                new Coordinate(-73.85, 40.78),
                new Coordinate(-73.95, 40.78)
        };
        transferwarehouseEntity.setRegionGeoJson(geometryFactory.createPolygon(geometryFactory.createLinearRing(coordinates)));

        transferwarehouseEntity.setLogisticsPartner("partner");
        transferwarehouseEntity.setLogisticsPartnerUrl("logisticspartnerurl");

        GeoCoordinateEntity geoCoordinateEntity= new GeoCoordinateEntity();
        geoCoordinateEntity.setLon(45.56);
        geoCoordinateEntity.setLat(53.53);

        transferwarehouseEntity.setLocationCoordinates(geoCoordinateEntity);


        transferwarehouseRepository.save(transferwarehouseEntity);

        assertEquals(transferwarehouseRepository.findByCode(transferwarehouseEntity.getCode()).getCode(),transferwarehouseEntity.getCode());
        transferwarehouseRepository.delete(transferwarehouseRepository.findByCode(transferwarehouseEntity.getCode()));
        assertNull(transferwarehouseRepository.findByCode(transferwarehouseEntity.getCode()));
    }


}
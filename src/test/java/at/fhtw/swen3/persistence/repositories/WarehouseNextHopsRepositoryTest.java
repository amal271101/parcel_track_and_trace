package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class WarehouseNextHopsRepositoryTest {
    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Autowired
    HopRepository hopRepository;

    @Autowired
    TruckRepository truckRepository;

    @Autowired
    GeoCoordinateRespository geoCoordinateRespository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Test
    void saveAndDeleteWarehouseNextHopsEntity() {
        WarehouseNextHopsEntity warehouseNextHopsEntity= new WarehouseNextHopsEntity();


        HopEntity hopEntity= new HopEntity();
        hopEntity.setDescription("This is a description");
        hopEntity.setCode("BLAA5");
        hopEntity.setHopType("hoptype");
        hopEntity.setLocationName("Floridsdorf");
        hopEntity.setProcessingDelayMins(23);

        GeoCoordinateEntity geoCoordinateEntity= new GeoCoordinateEntity();
        geoCoordinateEntity.setLon(45.56);
        geoCoordinateEntity.setLat(53.53);

        hopEntity.setLocationCoordinates(geoCoordinateEntity);

        warehouseNextHopsEntity.setHop(hopEntity);
        warehouseNextHopsEntity.setTraveltimeMins(60);

        warehouseNextHopsRepository.save(warehouseNextHopsEntity);

        assertEquals(warehouseNextHopsRepository.findByTraveltimeMins(warehouseNextHopsEntity.getTraveltimeMins()).getTraveltimeMins(),warehouseNextHopsEntity.getTraveltimeMins());

        warehouseNextHopsRepository.delete(warehouseNextHopsEntity);

        geoCoordinateRespository.delete(geoCoordinateEntity);
        assertNull(warehouseNextHopsRepository.findByTraveltimeMins(warehouseNextHopsEntity.getTraveltimeMins()));

    }

}


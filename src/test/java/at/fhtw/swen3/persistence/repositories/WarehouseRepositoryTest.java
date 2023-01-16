package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Test
    void saveAndDeleteHopEntity() {


        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setLevel(3);
        warehouseEntity.setDescription("This is a warehouse description");
        warehouseEntity.setCode("BLBA6");
        warehouseEntity.setHopType("warehouse");
        warehouseEntity.setLocationName("Vienna");
        warehouseEntity.setProcessingDelayMins(30);

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLon(48.21);
        geoCoordinateEntity.setLat(76.37);

        warehouseEntity.setLocationCoordinates(geoCoordinateEntity);


        HopEntity hopEntity= new HopEntity();
        hopEntity.setDescription("This is also a  description");
        hopEntity.setCode("BLBA5");
        hopEntity.setHopType("hopytype");
        hopEntity.setLocationName("Floridsdorf");
        hopEntity.setProcessingDelayMins(23);

        GeoCoordinateEntity geoCoordinateEntity2= new GeoCoordinateEntity();
        geoCoordinateEntity.setLon(45.56);
        geoCoordinateEntity.setLat(53.53);

        hopEntity.setLocationCoordinates(geoCoordinateEntity2);


        WarehouseNextHopsEntity nextHop = new WarehouseNextHopsEntity();
        nextHop.setTraveltimeMins(100);
        nextHop.setHop(hopEntity);

        List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();
        nextHops.add(nextHop);

        warehouseEntity.setNextHops(nextHops);
        warehouseNextHopsRepository.save(nextHop);
        warehouseRepository.save(warehouseEntity);
        assertEquals(warehouseRepository.findByCode(warehouseEntity.getCode()).getCode(),warehouseEntity.getCode());
        warehouseRepository.delete(warehouseRepository.findByCode(warehouseEntity.getCode()));
        warehouseNextHopsRepository.delete(nextHop);
        assertNull(warehouseRepository.findByCode(warehouseEntity.getCode()));
    }


}
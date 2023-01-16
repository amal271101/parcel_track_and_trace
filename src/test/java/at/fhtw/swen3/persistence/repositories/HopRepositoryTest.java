package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class HopRepositoryTest {

    @Autowired
    private HopRepository hopRepository;


    @Test
    void saveAndDeleteHopEntity() {

        HopEntity hopEntity= new HopEntity();
        hopEntity.setDescription("This is also a  description");
        hopEntity.setCode("BLBA5");
        hopEntity.setHopType("hopytype");
        hopEntity.setLocationName("Floridsdorf");
        hopEntity.setProcessingDelayMins(23);

        GeoCoordinateEntity geoCoordinateEntity= new GeoCoordinateEntity();
        geoCoordinateEntity.setLon(45.56);
        geoCoordinateEntity.setLat(53.53);

        hopEntity.setLocationCoordinates(geoCoordinateEntity);


        hopRepository.save(hopEntity);

        assertEquals(hopRepository.findByCode(hopEntity.getCode()).getCode(),hopEntity.getCode());
        hopRepository.delete(hopRepository.findByCode(hopEntity.getCode()));
        assertNull(hopRepository.findByCode(hopEntity.getCode()));
    }


}
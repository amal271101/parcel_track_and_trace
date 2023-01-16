package at.fhtw.swen3.persistence.repositories;


import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GeoCoordinateRespositoryTest {
     @Autowired
        private at.fhtw.swen3.persistence.repositories.GeoCoordinateRespository geoCoordinateRespository;
        @Test
        void saveAndDeleteGeoCoordinateEntity() {

            GeoCoordinateEntity geoCoordinateEntity= new GeoCoordinateEntity();
            geoCoordinateEntity.setId(1L);
            geoCoordinateEntity.setLon(53.56);
            geoCoordinateEntity.setLat(35.53);

            geoCoordinateRespository.save(geoCoordinateEntity);

            assertEquals(geoCoordinateRespository.findByLat(geoCoordinateEntity.getLat()).getLat(),geoCoordinateEntity.getLat());
            geoCoordinateRespository.delete(geoCoordinateRespository.findByLat(geoCoordinateEntity.getLat()));
            assertNull(geoCoordinateRespository.findByLat(geoCoordinateEntity.getLat()));
        }




}
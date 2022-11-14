package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.NewParcelInfoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class NewParcelInfoRespositoryTest {


    @Autowired
    private NewParcelInfoRespository newParcelInfoRespository;


    @Test
    void saveAndDeleteNewParcelInfoEntity() {
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();

        newParcelInfoEntity.setTrackingId("PYJRB4HZ6");


        newParcelInfoRespository.save(newParcelInfoEntity);


        assertEquals(newParcelInfoRespository.findByTrackingId(newParcelInfoEntity.getTrackingId()).getTrackingId(),newParcelInfoEntity.getTrackingId());
        newParcelInfoRespository.delete(newParcelInfoRespository.findByTrackingId(newParcelInfoEntity.getTrackingId()));
        assertNull(newParcelInfoRespository.findByTrackingId(newParcelInfoEntity.getTrackingId()));
    }

    }


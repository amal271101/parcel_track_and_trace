package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class HopArrivalRepositoryTest {

    @Autowired
    private HopArrivalRepository hopArrivalRepository;
    @Test
    void saveAndDeleteHopArrivalEntity() {

        HopArrivalEntity hopArrivalEntity= new HopArrivalEntity();
        hopArrivalEntity.setDateTime(OffsetDateTime.now());
        hopArrivalEntity.setDescription("This is a description");
        hopArrivalEntity.setCode("BLAA5");

        hopArrivalRepository.save(hopArrivalEntity);


        assertEquals(hopArrivalRepository.findByCode(hopArrivalEntity.getCode()).getCode(),hopArrivalEntity.getCode());
        hopArrivalRepository.delete(hopArrivalRepository.findByCode(hopArrivalEntity.getCode()));
        assertNull(hopArrivalRepository.findByCode(hopArrivalEntity.getCode()));
    }

}
package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HopArrivalMapperTest {
    @Test
    void EntitytoDto() {
        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();
        hopArrivalEntity.setDateTime(OffsetDateTime.now());
        hopArrivalEntity.setDescription("description");
        hopArrivalEntity.setCode("code");

        HopArrival hopArrival = HopArrivalMapper.INSTANCE.entityToDto(hopArrivalEntity);

        assertEquals(hopArrival.getCode(), hopArrivalEntity.getCode());
        assertEquals(hopArrival.getDescription(), hopArrivalEntity.getDescription());
        assertEquals(hopArrival.getDateTime(), hopArrivalEntity.getDateTime());


    }


    @Test
    void DtotoEntity() {
     HopArrival hopArrival= new HopArrival();
     hopArrival.setCode("code");
     hopArrival.setDateTime(OffsetDateTime.now());
     hopArrival.setDescription("helloooo");

     HopArrivalEntity hopArrivalEntity = HopArrivalMapper.INSTANCE.dtoToEntity(hopArrival);

        assertEquals(hopArrival.getCode(), hopArrivalEntity.getCode());
        assertEquals(hopArrival.getDescription(), hopArrivalEntity.getDescription());
        assertEquals(hopArrival.getDateTime(), hopArrivalEntity.getDateTime());

    }
}
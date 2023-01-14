package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.mapper.HopMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HopMapperTest {
    @Test
    void EntitytoDto() {
        HopEntity hopEntity = new HopEntity();
        hopEntity.setDescription("description");
        hopEntity.setCode("code");
        hopEntity.setHopType("type");

        Hop hop = HopMapper.INSTANCE.entityToDto(hopEntity);

        assertEquals(hop.getCode(), hopEntity.getCode());
        assertEquals(hop.getDescription(), hopEntity.getDescription());
        assertEquals(hop.getHopType(), hopEntity.getHopType());

    }


    @Test
    void DtotoEntity() {
        Hop hop= new Hop();
        hop.setCode("code");
        hop.setDescription("helloooo");
        hop.setHopType("type");

        HopEntity hopEntity = HopMapper.INSTANCE.dtoToEntity(hop);

        assertEquals(hop.getCode(), hopEntity.getCode());
        assertEquals(hop.getDescription(), hopEntity.getDescription());
        assertEquals(hop.getHopType(), hopEntity.getHopType());

    }

}
package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferwarehouseMapperTest {
    @Test
    void EntitytoDto() {
        TransferwarehouseEntity transferwarehouseEntity = new TransferwarehouseEntity();


        transferwarehouseEntity.setDescription("description");
        transferwarehouseEntity.setCode("code");
        transferwarehouseEntity.setHopType("type");


        Transferwarehouse transferwarehouse = TransferwarehouseMapper.INSTANCE.entityToDto(transferwarehouseEntity);

        assertEquals(transferwarehouse.getCode(), transferwarehouseEntity.getCode());
        assertEquals(transferwarehouse.getDescription(), transferwarehouseEntity.getDescription());
        assertEquals(transferwarehouse.getHopType(), transferwarehouseEntity.getHopType());

    }

    @Test
    void DtotoEntity() {
        Transferwarehouse transferwarehouse = new Transferwarehouse();
        transferwarehouse.setCode("code");
        transferwarehouse.setDescription("helloooo");
        transferwarehouse.setHopType("type");

        TransferwarehouseEntity transferwarehouseEntity = TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);

        assertEquals(transferwarehouse.getCode(), transferwarehouseEntity.getCode());
        assertEquals(transferwarehouse.getDescription(), transferwarehouseEntity.getDescription());
        assertEquals(transferwarehouse.getHopType(), transferwarehouseEntity.getHopType());

    }

}
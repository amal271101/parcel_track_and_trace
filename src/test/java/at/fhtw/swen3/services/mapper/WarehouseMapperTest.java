package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseMapperTest {
    @Test
    void EntitytoDto() {
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setDescription("description");
        warehouseEntity.setCode("code");
        warehouseEntity.setHopType("type");
        warehouseEntity.setLocationName("location");


        Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
        assertEquals(warehouse.getCode(), warehouseEntity.getCode());
        assertEquals(warehouse.getDescription(), warehouseEntity.getDescription());
        assertEquals(warehouse.getHopType(), warehouseEntity.getHopType());
        assertEquals(warehouse.getLocationName(), warehouseEntity.getLocationName());
    }


    @Test
    void DtotoEntity() {
       Warehouse warehouse = new Warehouse();
        warehouse.setDescription("description");
        warehouse.setCode("code");
        warehouse.setHopType("type");
        warehouse.setLocationName("location");

        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        assertEquals(warehouse.getCode(), warehouseEntity.getCode());
        assertEquals(warehouse.getDescription(), warehouseEntity.getDescription());
        assertEquals(warehouse.getHopType(), warehouseEntity.getHopType());
        assertEquals(warehouse.getLocationName(), warehouseEntity.getLocationName());
    }

}
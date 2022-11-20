package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseNextHopsMapperTest {

    @Test
    void EntitytoDto() {
        WarehouseNextHopsEntity warehouseNextHopsEntity = new WarehouseNextHopsEntity();
        HopEntity hopEntity = new HopEntity();
        hopEntity.setDescription("description");
        hopEntity.setCode("code");
        hopEntity.setHopType("type");
        warehouseNextHopsEntity.setHop(hopEntity);
        warehouseNextHopsEntity.setTraveltimeMins(3);

        WarehouseNextHops warehouseNextHops = WarehouseNextHopsMapper.INSTANCE.entityToDto(warehouseNextHopsEntity);



        assertEquals(warehouseNextHops.getHop().getHopType(), warehouseNextHopsEntity.getHop().getHopType());
        assertEquals(warehouseNextHops.getHop().getDescription(), warehouseNextHopsEntity.getHop().getDescription());

        assertEquals(warehouseNextHops.getTraveltimeMins(), warehouseNextHopsEntity.getTraveltimeMins());
    }


    @Test
    void DtotoEntity() {

        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();
        Hop hop = new Hop();
        hop.setDescription("description");
        hop.setCode("code");
        hop.setHopType("type");
        warehouseNextHops.setHop(hop);
        warehouseNextHops.setTraveltimeMins(3);

        WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsMapper.INSTANCE.dtoToEntity(warehouseNextHops);

        assertEquals(warehouseNextHops.getHop().getHopType(), warehouseNextHopsEntity.getHop().getHopType());
        assertEquals(warehouseNextHops.getHop().getDescription(), warehouseNextHopsEntity.getHop().getDescription());

        assertEquals(warehouseNextHops.getTraveltimeMins(), warehouseNextHopsEntity.getTraveltimeMins());

    }



}
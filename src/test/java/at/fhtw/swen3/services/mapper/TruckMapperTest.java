package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.mapper.TruckMapper;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TruckMapperTest {
    @Test
    void EntitytoDto() {
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setDescription("description");
        truckEntity.setCode("code");
        truckEntity.setHopType("type");
        truckEntity.setLocationName("location");

       Truck truck = TruckMapper.INSTANCE.entityToDto(truckEntity);

        assertEquals(truck.getCode(), truckEntity.getCode());
        assertEquals(truck.getDescription(), truckEntity.getDescription());
        assertEquals(truck.getHopType(), truckEntity.getHopType());
        assertEquals(truck.getLocationName(), truckEntity.getLocationName());

    }


    @Test
    void DtotoEntity() {
        Truck truck= new Truck();
        truck.setCode("code");
        truck.setDescription("helloooo");
        truck.setHopType("type");

        TruckEntity truckEntity = TruckMapper.INSTANCE.dtoToEntity(truck);

        assertEquals(truck.getCode(), truckEntity.getCode());
        assertEquals(truck.getDescription(), truckEntity.getDescription());
        assertEquals(truck.getHopType(), truckEntity.getHopType());
        assertEquals(truck.getLocationName(), truckEntity.getLocationName());
    }

}
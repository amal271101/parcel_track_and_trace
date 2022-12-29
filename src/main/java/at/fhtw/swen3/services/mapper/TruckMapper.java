package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(uses = HopMapper.class)

public interface TruckMapper {
    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);

    Truck entityToDto(TruckEntity truckEntityEntity);

    TruckEntity dtoToEntity(Truck truck);
}

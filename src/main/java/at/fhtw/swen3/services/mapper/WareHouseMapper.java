package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface WareHouseMapper {
    WareHouseMapper INSTANCE = Mappers.getMapper(WareHouseMapper.class);

    Warehouse entityToDto(WarehouseEntity warehouseEntity);

    WarehouseEntity dtoToEntity(Warehouse warehouse);

}

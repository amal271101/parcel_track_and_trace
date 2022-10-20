package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.WarehouseNextHopsEnitity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T17:27:18+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class WarehouseNextHopsMapperImpl implements WarehouseNextHopsMapper {

    @Override
    public WarehouseNextHops entityToDto(WarehouseNextHopsEnitity warehouseNextHopsEnitity) {
        if ( warehouseNextHopsEnitity == null ) {
            return null;
        }

        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();

        warehouseNextHops.setTraveltimeMins( warehouseNextHopsEnitity.getTraveltimeMins() );
        warehouseNextHops.setHop( warehouseNextHopsEnitity.getHop() );

        return warehouseNextHops;
    }

    @Override
    public WarehouseNextHopsEnitity dtoToEntity(WarehouseNextHops warehouseNextHops) {
        if ( warehouseNextHops == null ) {
            return null;
        }

        Integer traveltimeMins = null;
        Hop hop = null;

        traveltimeMins = warehouseNextHops.getTraveltimeMins();
        hop = warehouseNextHops.getHop();

        WarehouseNextHopsEnitity warehouseNextHopsEnitity = new WarehouseNextHopsEnitity( traveltimeMins, hop );

        return warehouseNextHopsEnitity;
    }
}

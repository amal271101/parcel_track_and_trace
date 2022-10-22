package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-22T19:20:01+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class WarehouseNextHopsMapperImpl implements WarehouseNextHopsMapper {

    @Override
    public WarehouseNextHops entityToDto(WarehouseNextHopsEntity warehouseNextHopsEntity) {
        if ( warehouseNextHopsEntity == null ) {
            return null;
        }

        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();

        warehouseNextHops.setTraveltimeMins( warehouseNextHopsEntity.getTraveltimeMins() );
        warehouseNextHops.setHop( warehouseNextHopsEntity.getHop() );

        return warehouseNextHops;
    }

    @Override
    public WarehouseNextHopsEntity dtoToEntity(WarehouseNextHops warehouseNextHops) {
        if ( warehouseNextHops == null ) {
            return null;
        }

        Integer traveltimeMins = null;
        Hop hop = null;

        traveltimeMins = warehouseNextHops.getTraveltimeMins();
        hop = warehouseNextHops.getHop();

        WarehouseNextHopsEntity warehouseNextHopsEntity = new WarehouseNextHopsEntity( traveltimeMins, hop );

        return warehouseNextHopsEntity;
    }
}

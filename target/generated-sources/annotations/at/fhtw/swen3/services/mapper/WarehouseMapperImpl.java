package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-05T21:07:53+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
public class WarehouseMapperImpl implements WarehouseMapper {

    private final HopMapper hopMapper = Mappers.getMapper( HopMapper.class );

    @Override
    public Warehouse entityToDto(WarehouseEntity warehouseEntity) {
        if ( warehouseEntity == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        warehouse.hopType( warehouseEntity.getHopType() );
        warehouse.code( warehouseEntity.getCode() );
        warehouse.description( warehouseEntity.getDescription() );
        warehouse.processingDelayMins( warehouseEntity.getProcessingDelayMins() );
        warehouse.locationName( warehouseEntity.getLocationName() );
        warehouse.locationCoordinates( geoCoordinateEntityToGeoCoordinate( warehouseEntity.getLocationCoordinates() ) );
        warehouse.setLevel( warehouseEntity.getLevel() );
        warehouse.setNextHops( warehouseNextHopsEntityListToWarehouseNextHopsList( warehouseEntity.getNextHops() ) );

        return warehouse;
    }

    @Override
    public WarehouseEntity dtoToEntity(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseEntity warehouseEntity = new WarehouseEntity();

        warehouseEntity.setCode( warehouse.getCode() );
        warehouseEntity.setHopType( warehouse.getHopType() );
        warehouseEntity.setDescription( warehouse.getDescription() );
        warehouseEntity.setProcessingDelayMins( warehouse.getProcessingDelayMins() );
        warehouseEntity.setLocationName( warehouse.getLocationName() );
        warehouseEntity.setLocationCoordinates( geoCoordinateToGeoCoordinateEntity( warehouse.getLocationCoordinates() ) );
        warehouseEntity.setLevel( warehouse.getLevel() );
        warehouseEntity.setNextHops( warehouseNextHopsListToWarehouseNextHopsEntityList( warehouse.getNextHops() ) );

        return warehouseEntity;
    }

    protected GeoCoordinate geoCoordinateEntityToGeoCoordinate(GeoCoordinateEntity geoCoordinateEntity) {
        if ( geoCoordinateEntity == null ) {
            return null;
        }

        GeoCoordinate geoCoordinate = new GeoCoordinate();

        geoCoordinate.setLat( geoCoordinateEntity.getLat() );
        geoCoordinate.setLon( geoCoordinateEntity.getLon() );

        return geoCoordinate;
    }

    protected WarehouseNextHops warehouseNextHopsEntityToWarehouseNextHops(WarehouseNextHopsEntity warehouseNextHopsEntity) {
        if ( warehouseNextHopsEntity == null ) {
            return null;
        }

        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();

        warehouseNextHops.setTraveltimeMins( warehouseNextHopsEntity.getTraveltimeMins() );
        warehouseNextHops.setHop( hopMapper.entityToDto( warehouseNextHopsEntity.getHop() ) );

        return warehouseNextHops;
    }

    protected List<WarehouseNextHops> warehouseNextHopsEntityListToWarehouseNextHopsList(List<WarehouseNextHopsEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<WarehouseNextHops> list1 = new ArrayList<WarehouseNextHops>( list.size() );
        for ( WarehouseNextHopsEntity warehouseNextHopsEntity : list ) {
            list1.add( warehouseNextHopsEntityToWarehouseNextHops( warehouseNextHopsEntity ) );
        }

        return list1;
    }

    protected GeoCoordinateEntity geoCoordinateToGeoCoordinateEntity(GeoCoordinate geoCoordinate) {
        if ( geoCoordinate == null ) {
            return null;
        }

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();

        geoCoordinateEntity.setLat( geoCoordinate.getLat() );
        geoCoordinateEntity.setLon( geoCoordinate.getLon() );

        return geoCoordinateEntity;
    }

    protected WarehouseNextHopsEntity warehouseNextHopsToWarehouseNextHopsEntity(WarehouseNextHops warehouseNextHops) {
        if ( warehouseNextHops == null ) {
            return null;
        }

        WarehouseNextHopsEntity warehouseNextHopsEntity = new WarehouseNextHopsEntity();

        warehouseNextHopsEntity.setTraveltimeMins( warehouseNextHops.getTraveltimeMins() );
        warehouseNextHopsEntity.setHop( hopMapper.dtoToEntity( warehouseNextHops.getHop() ) );

        return warehouseNextHopsEntity;
    }

    protected List<WarehouseNextHopsEntity> warehouseNextHopsListToWarehouseNextHopsEntityList(List<WarehouseNextHops> list) {
        if ( list == null ) {
            return null;
        }

        List<WarehouseNextHopsEntity> list1 = new ArrayList<WarehouseNextHopsEntity>( list.size() );
        for ( WarehouseNextHops warehouseNextHops : list ) {
            list1.add( warehouseNextHopsToWarehouseNextHopsEntity( warehouseNextHops ) );
        }

        return list1;
    }
}

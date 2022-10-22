package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.WarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-22T16:37:25+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class WareHouseMapperImpl implements WareHouseMapper {

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
        List<WarehouseNextHops> list = warehouseEntity.getNextHops();
        if ( list != null ) {
            warehouse.setNextHops( new ArrayList<WarehouseNextHops>( list ) );
        }

        return warehouse;
    }

    @Override
    public WarehouseEntity dtoToEntity(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        Integer level = null;
        List<WarehouseNextHops> nextHops = null;
        String hopType = null;
        String code = null;
        String description = null;
        Integer processingDelayMins = null;
        String locationName = null;
        GeoCoordinateEntity locationCoordinates = null;

        level = warehouse.getLevel();
        List<WarehouseNextHops> list = warehouse.getNextHops();
        if ( list != null ) {
            nextHops = new ArrayList<WarehouseNextHops>( list );
        }
        hopType = warehouse.getHopType();
        code = warehouse.getCode();
        description = warehouse.getDescription();
        processingDelayMins = warehouse.getProcessingDelayMins();
        locationName = warehouse.getLocationName();
        locationCoordinates = geoCoordinateToGeoCoordinateEntity( warehouse.getLocationCoordinates() );

        WarehouseEntity warehouseEntity = new WarehouseEntity( level, nextHops, hopType, code, description, processingDelayMins, locationName, locationCoordinates );

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

    protected GeoCoordinateEntity geoCoordinateToGeoCoordinateEntity(GeoCoordinate geoCoordinate) {
        if ( geoCoordinate == null ) {
            return null;
        }

        Double lat = null;
        Double lon = null;

        lat = geoCoordinate.getLat();
        lon = geoCoordinate.getLon();

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity( lat, lon );

        return geoCoordinateEntity;
    }
}

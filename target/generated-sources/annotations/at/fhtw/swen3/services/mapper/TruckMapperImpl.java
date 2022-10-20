package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.TruckEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Truck;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T17:27:20+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class TruckMapperImpl implements TruckMapper {

    @Override
    public Truck entityToDto(TruckEntity truckEntityEntity) {
        if ( truckEntityEntity == null ) {
            return null;
        }

        Truck truck = new Truck();

        truck.hopType( truckEntityEntity.getHopType() );
        truck.code( truckEntityEntity.getCode() );
        truck.description( truckEntityEntity.getDescription() );
        truck.processingDelayMins( truckEntityEntity.getProcessingDelayMins() );
        truck.locationName( truckEntityEntity.getLocationName() );
        truck.locationCoordinates( geoCoordinateEntityToGeoCoordinate( truckEntityEntity.getLocationCoordinates() ) );
        truck.setRegionGeoJson( truckEntityEntity.getRegionGeoJson() );
        truck.setNumberPlate( truckEntityEntity.getNumberPlate() );

        return truck;
    }

    @Override
    public TruckEntity dtoToEntity(Truck truck) {
        if ( truck == null ) {
            return null;
        }

        String regionGeoJson = null;
        String numberPlate = null;
        String hopType = null;
        String code = null;
        String description = null;
        String locationName = null;
        Integer processingDelayMins = null;
        GeoCoordinateEntity locationCoordinates = null;

        regionGeoJson = truck.getRegionGeoJson();
        numberPlate = truck.getNumberPlate();
        hopType = truck.getHopType();
        code = truck.getCode();
        description = truck.getDescription();
        locationName = truck.getLocationName();
        processingDelayMins = truck.getProcessingDelayMins();
        locationCoordinates = geoCoordinateToGeoCoordinateEntity( truck.getLocationCoordinates() );

        TruckEntity truckEntity = new TruckEntity( regionGeoJson, numberPlate, hopType, code, description, locationName, processingDelayMins, locationCoordinates );

        return truckEntity;
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

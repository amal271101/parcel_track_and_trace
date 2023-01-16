package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Truck;
import javax.annotation.processing.Generated;
import org.locationtech.jts.io.ParseException;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-16T00:41:36+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
public class TruckMapperImpl implements TruckMapper {

    @Override
    public Truck entityToDto(TruckEntity truckEntityEntity) {
        if ( truckEntityEntity == null ) {
            return null;
        }

        Truck truck = new Truck();

        truck.setRegionGeoJson( convertGeometryObjectToString( truckEntityEntity.getRegionGeoJson() ) );
        truck.hopType( truckEntityEntity.getHopType() );
        truck.code( truckEntityEntity.getCode() );
        truck.description( truckEntityEntity.getDescription() );
        truck.processingDelayMins( truckEntityEntity.getProcessingDelayMins() );
        truck.locationName( truckEntityEntity.getLocationName() );
        truck.locationCoordinates( geoCoordinateEntityToGeoCoordinate( truckEntityEntity.getLocationCoordinates() ) );
        truck.setNumberPlate( truckEntityEntity.getNumberPlate() );

        return truck;
    }

    @Override
    public TruckEntity dtoToEntity(Truck truck) {
        if ( truck == null ) {
            return null;
        }

        TruckEntity truckEntity = new TruckEntity();

        try {
            truckEntity.setRegionGeoJson( convertStringToGeometryObject( truck.getRegionGeoJson() ) );
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        truckEntity.setCode( truck.getCode() );
        truckEntity.setHopType( truck.getHopType() );
        truckEntity.setDescription( truck.getDescription() );
        truckEntity.setProcessingDelayMins( truck.getProcessingDelayMins() );
        truckEntity.setLocationName( truck.getLocationName() );
        truckEntity.setLocationCoordinates( geoCoordinateToGeoCoordinateEntity( truck.getLocationCoordinates() ) );
        truckEntity.setNumberPlate( truck.getNumberPlate() );

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

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();

        geoCoordinateEntity.setLat( geoCoordinate.getLat() );
        geoCoordinateEntity.setLon( geoCoordinate.getLon() );

        return geoCoordinateEntity;
    }
}

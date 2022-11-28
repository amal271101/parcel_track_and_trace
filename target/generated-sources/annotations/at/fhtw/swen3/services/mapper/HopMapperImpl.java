package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
<<<<<<< Updated upstream
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-19T22:34:51+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
=======
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T07:43:50+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
>>>>>>> Stashed changes
)
public class HopMapperImpl implements HopMapper {

    @Override
    public Hop entityToDto(HopEntity hopEntity) {
        if ( hopEntity == null ) {
            return null;
        }

        Hop hop = new Hop();

        hop.setHopType( hopEntity.getHopType() );
        hop.setCode( hopEntity.getCode() );
        hop.setDescription( hopEntity.getDescription() );
        hop.setProcessingDelayMins( hopEntity.getProcessingDelayMins() );
        hop.setLocationName( hopEntity.getLocationName() );
        hop.setLocationCoordinates( geoCoordinateEntityToGeoCoordinate( hopEntity.getLocationCoordinates() ) );

        return hop;
    }

    @Override
    public HopEntity dtoToEntity(Hop hop) {
        if ( hop == null ) {
            return null;
        }

        HopEntity hopEntity = new HopEntity();

        hopEntity.setCode( hop.getCode() );
        hopEntity.setHopType( hop.getHopType() );
        hopEntity.setDescription( hop.getDescription() );
        hopEntity.setProcessingDelayMins( hop.getProcessingDelayMins() );
        hopEntity.setLocationName( hop.getLocationName() );
        hopEntity.setLocationCoordinates( geoCoordinateToGeoCoordinateEntity( hop.getLocationCoordinates() ) );

        return hopEntity;
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

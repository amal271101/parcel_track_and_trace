package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T17:27:20+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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
        hop.setLocationCoordinates( hopEntity.getLocationCoordinates() );

        return hop;
    }

    @Override
    public HopEntity dtoToEntity(Hop hop) {
        if ( hop == null ) {
            return null;
        }

        String hopType = null;
        String code = null;
        String description = null;
        Integer processingDelayMins = null;
        String locationName = null;
        GeoCoordinate locationCoordinates = null;

        hopType = hop.getHopType();
        code = hop.getCode();
        description = hop.getDescription();
        processingDelayMins = hop.getProcessingDelayMins();
        locationName = hop.getLocationName();
        locationCoordinates = hop.getLocationCoordinates();

        HopEntity hopEntity = new HopEntity( hopType, code, description, processingDelayMins, locationName, locationCoordinates );

        return hopEntity;
    }
}

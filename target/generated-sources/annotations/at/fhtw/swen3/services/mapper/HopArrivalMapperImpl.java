package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.HopArrival;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T17:27:20+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class HopArrivalMapperImpl implements HopArrivalMapper {

    @Override
    public HopArrival entityToDto(HopArrivalEntity hopArrivalEntity) {
        if ( hopArrivalEntity == null ) {
            return null;
        }

        HopArrival hopArrival = new HopArrival();

        hopArrival.setCode( hopArrivalEntity.getCode() );
        hopArrival.setDescription( hopArrivalEntity.getDescription() );
        hopArrival.setDateTime( hopArrivalEntity.getDateTime() );

        return hopArrival;
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

package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
<<<<<<< Updated upstream
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-19T22:34:50+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
=======
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T07:43:49+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
>>>>>>> Stashed changes
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
    public HopArrivalEntity dtoToEntity(HopArrival hop) {
        if ( hop == null ) {
            return null;
        }

        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();

        hopArrivalEntity.setCode( hop.getCode() );
        hopArrivalEntity.setDescription( hop.getDescription() );
        hopArrivalEntity.setDateTime( hop.getDateTime() );

        return hopArrivalEntity;
    }
}

package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-12T01:05:43+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class ErrorMapperImpl implements ErrorMapper {

    @Override
    public Error entityToDto(ErrorEntity errorEntity) {
        if ( errorEntity == null ) {
            return null;
        }

        Error error = new Error();

        error.setErrorMessage( errorEntity.getErrorMessage() );

        return error;
    }

    @Override
    public ErrorEntity dtoToEntity(Error error) {
        if ( error == null ) {
            return null;
        }

        ErrorEntity errorEntity = new ErrorEntity();

        errorEntity.setErrorMessage( error.getErrorMessage() );

        return errorEntity;
    }
}

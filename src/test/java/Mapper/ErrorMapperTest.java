package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMapperTest {
    @Test
    void EntitytoDto() {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorMessage("this is an error");
        Error error = ErrorMapper.INSTANCE.entityToDto(errorEntity);
        assertEquals(errorEntity.getErrorMessage(), error.getErrorMessage());
    }


    @Test
    void DtotoEntity() {
        Error error = new Error();
        error.setErrorMessage("error");
        ErrorEntity errorEntity = ErrorMapper.INSTANCE.dtoToEntity(error);
        assertEquals(errorEntity.getErrorMessage(), error.getErrorMessage());
        assertEquals(errorEntity.getErrorMessage(), error.getErrorMessage());
    }

}
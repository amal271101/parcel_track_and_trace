package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import lombok.Getter;

@Getter
public class BLException extends Exception {
    private Exception innerException;

    private ErrorEntity errorEntity;

    public BLException(Exception innerException, String errorMessage) {
        this.innerException = innerException;
        this.errorEntity= new ErrorEntity();
        this.errorEntity.setErrorMessage(errorMessage);

    }

    @Override
    public String getMessage(){
        return errorEntity.getErrorMessage();
    }

}



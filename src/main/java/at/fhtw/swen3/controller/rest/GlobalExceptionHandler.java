package at.fhtw.swen3.controller.rest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import at.fhtw.swen3.services.dto.Error;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
   @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        Error error = new Error();
        error.setErrorMessage("There is an issue with the provided Json ");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGeneralException(Exception e) {
        log.error(e.getMessage());
        Error error = new Error();
        error.setErrorMessage("The operation failed due to an error.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
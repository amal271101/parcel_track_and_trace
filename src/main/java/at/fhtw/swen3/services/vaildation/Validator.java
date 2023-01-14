package at.fhtw.swen3.services.vaildation;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import at.fhtw.swen3.services.BLValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component

public class Validator {
    private static final Logger log = LoggerFactory.getLogger(ParcelApiController.class);

    static ValidatorFactory getValidatorFactory() {

        return Validation.buildDefaultValidatorFactory();
    }

    javax.validation.Validator getValidator() {
        return getValidatorFactory().getValidator();
    }

   public  <T> void validate(T o) throws BLValidationException {
        javax.validation.Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> v : violations) {
                log.error(v.getMessage());
                throw new BLValidationException( null, v.getMessage());
            }
        }
    }
}

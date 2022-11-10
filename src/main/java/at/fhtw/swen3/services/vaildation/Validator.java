package at.fhtw.swen3.services.vaildation;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
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

   public  <T> boolean validate(T o) {
        javax.validation.Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            violations.forEach(v -> log.error(v.getMessage()));
            //throw new ConstraintViolationException(violations);
            return false;
        }
        return true;

    }
}

package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.WarehouseApi;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Generated;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-24T11:01:08.846404Z[Etc/UTC]")
@Controller
public class WarehouseApiController implements WarehouseApi {
    private static final Logger log = LoggerFactory.getLogger(ParcelApiController.class);
    private final NativeWebRequest request;

    @Autowired
    public WarehouseApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<Warehouse> exportWarehouses() {
        Warehouse warehouse =new Warehouse();
        warehouse.setLevel(4);
        warehouse.setHopType("Uno");
        warehouse.setCode("Hello World");

        Warehouse warehouse2 =new Warehouse();
        warehouse2.setLevel(4);
        warehouse2.setHopType("Uno");

        List<Warehouse> list = new ArrayList<>();
        list.add(warehouse);
        list.add(warehouse2);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

       for (Warehouse W : list) {
          WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(W);
            Set<ConstraintViolation<WarehouseEntity>> violations = validator.validate(warehouseEntity);
            if (violations.size() != 0) {
                for (ConstraintViolation<WarehouseEntity> violation : violations) {
                    log.error(violation.getMessage());
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Hop> getWarehouse(String code
    ) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Warehouse warehouse =new Warehouse();
        warehouse.setCode(code);

        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        Set<ConstraintViolation<WarehouseEntity>> violations = validator.validate(warehouseEntity);
        if (violations.size() != 0) {
            for (ConstraintViolation<WarehouseEntity> violation : violations) {
                log.error(violation.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        Set<ConstraintViolation<WarehouseEntity>> violations = validator.validate(warehouseEntity);
        if (violations.size() != 0) {
            for (ConstraintViolation<WarehouseEntity> violation : violations) {
                log.error(violation.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import at.fhtw.swen3.services.mapper.HopMapperDecorator;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-24T11:01:08.846404Z[Etc/UTC]")
@Controller
@Slf4j
public class WarehouseApiController implements WarehouseApi {

    private WarehouseService warehouseService;

    private final NativeWebRequest request;

    @Autowired
    public WarehouseApiController(NativeWebRequest request, WarehouseService warehouseService) {

        this.request = request;
        this.warehouseService=warehouseService;
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


    /*  public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.ParcelDtoToEntity(parcel);

        NewParcelInfo newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(parcelService.createParcel(parcelEntity));

        if(newParcelInfo!=null){
            return new ResponseEntity<>(newParcelInfo,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    * */
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        HopEntity warehouseEntity1 = HopMapperDecorator.INSTANCE.dtoToEntity(warehouse.getNextHops().get(0).getHop());

        WarehouseEntity warehouseEntity2 = (WarehouseEntity) warehouseEntity1;
        System.out.println(warehouseEntity2.getCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

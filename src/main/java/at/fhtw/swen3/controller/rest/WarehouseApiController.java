package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.services.BLDataNotFoundException;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;
import javax.annotation.Generated;


@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-24T11:01:08.846404Z[Etc/UTC]")
@Controller
@Slf4j
public class WarehouseApiController implements WarehouseApi {
    private final TruckRepository truckRepository;

    private WarehouseService warehouseService;

    private final NativeWebRequest request;

    @Autowired
    public WarehouseApiController(NativeWebRequest request, WarehouseService warehouseService, TruckRepository truckRepository) {

        this.request = request;
        this.warehouseService = warehouseService;
        this.truckRepository = truckRepository;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<?> exportWarehouses() {
        Warehouse warehouse = null;
        try {
            warehouse = WarehouseMapper.INSTANCE.entityToDto(warehouseService.exportWarehouses());
        } catch (BLException e) {
            log.error(e.getMessage());
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            if (e instanceof BLDataNotFoundException) {
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }


    public ResponseEntity<?> getWarehouse(String code
    ) {
        Error error = new Error();
        Object hop = null;
        try {
            hop = warehouseService.getWarehousebyCode(code);
        } catch (BLException e) {
            error.setErrorMessage(e.getMessage());
            if (e instanceof BLDataNotFoundException) {
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        if (hop.getClass() == WarehouseEntity.class) {
            Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto((WarehouseEntity) hop);
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } else if (hop.getClass() == TruckEntity.class) {
            Truck truck = TruckMapper.INSTANCE.entityToDto((TruckEntity) hop);
            return new ResponseEntity<>(truck, HttpStatus.OK);
        } else if (hop.getClass() == TransferwarehouseEntity.class) {
            Transferwarehouse transferwarehouse = TransferwarehouseMapper.INSTANCE.entityToDto((TransferwarehouseEntity) hop);
            return new ResponseEntity<>(transferwarehouse, HttpStatus.OK);
        } else {
            error.setErrorMessage("The operation failed due to an error.");
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }

    }


    public ResponseEntity<?> importWarehouses(Warehouse warehouse) {
        Error error = new Error();
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        try {
            warehouseService.importWarehouses(warehouseEntity);

        } catch (BLException e) {
            error.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
}

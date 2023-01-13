package at.fhtw.swen3.controller.rest;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.*;
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
    public WarehouseApiController(NativeWebRequest request, WarehouseService warehouseService,
                                  TruckRepository truckRepository) {

        this.request = request;
        this.warehouseService=warehouseService;
        this.truckRepository = truckRepository;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<Warehouse> exportWarehouses() {
        Warehouse warehouse= WarehouseMapper.INSTANCE.entityToDto(warehouseService.exportWarehouses());
        return new ResponseEntity<>(warehouse,HttpStatus.OK);
    }

    public ResponseEntity<Hop> getWarehouse(String code
    ) {
       Object hop= warehouseService.getWarehousebyCode(code);
        /** TO DO: VALIDATION**/
        if (hop.getClass()== WarehouseEntity.class) {
            Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto((WarehouseEntity) hop);
            return new ResponseEntity<>(warehouse,HttpStatus.OK);
        } else if (hop.getClass()== TruckEntity.class) {
            Truck truck = TruckMapper.INSTANCE.entityToDto((TruckEntity) hop);
            return new ResponseEntity<>(truck,HttpStatus.OK);
        } else if (hop.getClass()==TransferwarehouseEntity.class) {
            Transferwarehouse transferwarehouse = TransferwarehouseMapper.INSTANCE.entityToDto((TransferwarehouseEntity) hop);
            return new ResponseEntity<>(transferwarehouse,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
       if(warehouseService.importWarehouses(warehouseEntity)){return new ResponseEntity<>(HttpStatus.OK);}
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

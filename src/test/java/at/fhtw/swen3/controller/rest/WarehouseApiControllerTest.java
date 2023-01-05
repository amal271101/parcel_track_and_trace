package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.controller.rest.WarehouseApiController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehouseApiControllerTest {

  /*  @Test
    void exportWarehousesTestFail(){
        WarehouseApiController wareHouseApiController = new WarehouseApiController(null, null);
        ResponseEntity<Warehouse> responseEntity = wareHouseApiController.exportWarehouses();
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }

    @Test
    void getWarehouseTestFail(){

        WarehouseApiController wareHouseApiController = new WarehouseApiController(null, null);
        ResponseEntity<Hop> responseEntity = wareHouseApiController.getWarehouse("code");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }

    @Test
    void importWarehousesFail(){
        Warehouse warehouse =new Warehouse();
        warehouse.setCode("code");

        WarehouseApiController wareHouseApiController = new WarehouseApiController(null, null);
        ResponseEntity<Void> responseEntity = wareHouseApiController.importWarehouses(warehouse);
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }

*/



}

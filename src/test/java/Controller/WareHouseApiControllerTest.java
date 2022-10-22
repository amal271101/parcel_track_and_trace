package Controller;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import at.fhtw.swen3.services.impl.WarehouseApiController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WareHouseApiControllerTest {

    @Test
    void exportWarehousesTestFail(){
        WarehouseApiController wareHouseApiController = new WarehouseApiController(null);
        ResponseEntity<Warehouse> responseEntity = wareHouseApiController.exportWarehouses();
            assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED).getStatusCode());
    }

    @Test
    void getWarehouseTestFail(){

        WarehouseApiController wareHouseApiController = new WarehouseApiController(null);
        ResponseEntity<Hop> responseEntity = wareHouseApiController.getWarehouse("code");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED).getStatusCode());
    }

    @Test
    void importWarehousesFail(){
        Warehouse warehouse =new Warehouse();
        warehouse.setCode("code");

        WarehouseApiController wareHouseApiController = new WarehouseApiController(null);
        ResponseEntity<Void> responseEntity = wareHouseApiController.importWarehouses(warehouse);
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED).getStatusCode());
    }

    @Test
    void importWarehousesSuccess(){
        Warehouse warehouse =new Warehouse();
        warehouse.setCode("code");
        warehouse.setLocationName("name");
        warehouse.setDescription("helllo27873");
        warehouse.setProcessingDelayMins(4);
        warehouse.setHopType("hoptype");

        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLon(23.9);
        geoCoordinate.setLat(3.8);

        warehouse.setLocationCoordinates(geoCoordinate);

        List<WarehouseNextHops> warehouseNextHopsList = new ArrayList<>();

        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();

        Hop hop = new Hop();
        hop.setCode("code");
        hop.setLocationName("name");
        hop.setDescription("helllo27873");
        hop.setProcessingDelayMins(4);
        hop.setHopType("hoptype");

        warehouseNextHops.setHop(hop);
        warehouseNextHops.setTraveltimeMins(34);

        warehouseNextHopsList.add(warehouseNextHops);


        warehouse.setNextHops(warehouseNextHopsList);

        WarehouseApiController wareHouseApiController = new WarehouseApiController(null);
        ResponseEntity<Void> responseEntity = wareHouseApiController.importWarehouses(warehouse);
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.OK).getStatusCode());
    }



}

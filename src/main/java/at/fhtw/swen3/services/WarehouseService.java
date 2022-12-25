package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.stereotype.Service;

public interface WarehouseService {

    public void exportWarehouses();

    public void getWarehousebyCode();

    public boolean importWarehouses(WarehouseEntity warehouseEntity);

}

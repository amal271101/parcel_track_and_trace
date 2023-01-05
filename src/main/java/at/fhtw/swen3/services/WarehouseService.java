package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;

public interface WarehouseService {

    public WarehouseEntity exportWarehouses();

    public Object getWarehousebyCode(String code);

    public boolean importWarehouses(WarehouseEntity warehouseEntity);

}

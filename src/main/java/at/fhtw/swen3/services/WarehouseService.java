package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;

public interface WarehouseService {

    public  WarehouseEntity exportWarehouses() throws BLException;

    public Object getWarehousebyCode(String code) throws BLException;

    public void importWarehouses(WarehouseEntity warehouseEntity) throws BLException;

}

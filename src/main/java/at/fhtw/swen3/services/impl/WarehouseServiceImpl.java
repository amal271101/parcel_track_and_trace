package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

  private WarehouseRepository warehouseRepository;
  private WarehouseNextHopsRepository warehouseNextHopsRepository;
  private Validator myValidator;

    @Override
    public void exportWarehouses() {

    }

    @Override
    public void getWarehousebyCode() {

    }

    @Override
    public void importWarehouses() {

    }
}

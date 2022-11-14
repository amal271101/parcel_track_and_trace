package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final WarehouseNextHopsRepository warehouseNextHopsRepository;
  private final Validator myValidator;

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

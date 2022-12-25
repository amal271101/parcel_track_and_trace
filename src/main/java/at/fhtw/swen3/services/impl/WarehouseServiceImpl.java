package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    public boolean importWarehouses(WarehouseEntity warehouseEntity) {


      if (!myValidator.validate(warehouseEntity)) {
        return false;
      }

      warehouseNextHopsRepository.save(warehouseEntity.getNextHops().get(1));

     /* for (WarehouseNextHopsEntity hop : warehouseEntity.getNextHops()) {
        warehouseNextHopsRepository.save(hop);

      }*/
      return true;
    }
}

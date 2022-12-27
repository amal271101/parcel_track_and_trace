package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final WarehouseNextHopsRepository warehouseNextHopsRepository;

  private final Validator myValidator;
  private final HopRepository hopRepository;

  private final  GeoCoordinateRespository geoCoordinateRespository;

  private final TruckRepository truckRepository;

  private final TransferwarehouseRepository transferwarehouseRepository;


  @Override
    public void exportWarehouses() {

    }

    @Override
    public void getWarehousebyCode() {

    }

    @Override
    public boolean importWarehouses(WarehouseEntity warehouseEntity) {


     /* if (!myValidator.validate(warehouseEntity)) {
        return false;
      }*/

      geoCoordinateRespository.save(warehouseEntity.getNextHops().get(0).getHop().getLocationCoordinates());
      hopRepository.save(warehouseEntity.getNextHops().get(0).getHop());
      warehouseNextHopsRepository.save(warehouseEntity.getNextHops().get(0));



      //warehouseRepository.save(warehouseEntity);
     /* for (WarehouseNextHopsEntity hop : warehouseEntity.getNextHops()) {
        warehouseNextHopsRepository.save(hop);

      }*/
      return true;
    }
}

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



  public void saveNestedWarehouses(List<WarehouseNextHopsEntity> nextHops) {
    for (WarehouseNextHopsEntity nextHop : nextHops) {
      switch (nextHop.getHop().getHopType()) {
        case "warehouse":
          geoCoordinateRespository.save(nextHop.getHop().getLocationCoordinates());
          saveNestedWarehouses(((WarehouseEntity) nextHop.getHop()).getNextHops());
          break;
        case "truck":
          geoCoordinateRespository.save(nextHop.getHop().getLocationCoordinates());
          truckRepository.save((TruckEntity) nextHop.getHop());
          break;
        case "transferwarehouse":
          geoCoordinateRespository.save(nextHop.getHop().getLocationCoordinates());
          transferwarehouseRepository.save((TransferwarehouseEntity) nextHop.getHop());
          break;
        default:
      }
      //hopRepository.save(nextHop.getHop());
      warehouseNextHopsRepository.save(  nextHop);
    }
  }
    @Override
    public boolean importWarehouses(WarehouseEntity warehouseEntity) {
      if (!myValidator.validate(warehouseEntity)) {
        return false;
      }
      saveNestedWarehouses(warehouseEntity.getNextHops());
      geoCoordinateRespository.save(warehouseEntity.getLocationCoordinates());
      warehouseRepository.save(warehouseEntity);
      return true;
    }
}

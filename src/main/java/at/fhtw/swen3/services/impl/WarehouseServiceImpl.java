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

    private final GeoCoordinateRespository geoCoordinateRepository;

    private final TruckRepository truckRepository;

    private final TransferwarehouseRepository transferwarehouseRepository;

    public void deleteWarehouses(){
        warehouseRepository.deleteAllWarehouseEntityNextHops();
        warehouseNextHopsRepository.deleteAllWarehouseNextHops();
        warehouseRepository.deleteAllWarehouses();
        truckRepository.deleteAll();
        transferwarehouseRepository.deleteAll();
    }

    @Override
    public WarehouseEntity exportWarehouses() {
        return warehouseRepository.findByLevel(0);
    }

    @Override
    public Object getWarehousebyCode(String code) {
        if (truckRepository.findByCode(code) != null) {
            return truckRepository.findByCode(code);
        } else if (warehouseRepository.findByCode(code) != null) {
            return warehouseRepository.findByCode(code);
        } else if (transferwarehouseRepository.findByCode(code) != null) {
            return transferwarehouseRepository.findByCode(code);
        }
        return null;
    }

    public void saveNestedWarehouses(List<WarehouseNextHopsEntity> nextHops) {
        for (WarehouseNextHopsEntity nextHop : nextHops) {
            switch (nextHop.getHop().getHopType()) {
                case "warehouse":
                    geoCoordinateRepository.save(nextHop.getHop().getLocationCoordinates());
                    saveNestedWarehouses(((WarehouseEntity) nextHop.getHop()).getNextHops());
                    break;
                case "truck":
                    geoCoordinateRepository.save(nextHop.getHop().getLocationCoordinates());
                    truckRepository.save((TruckEntity) nextHop.getHop());
                    break;
                case "transferwarehouse":
                    geoCoordinateRepository.save(nextHop.getHop().getLocationCoordinates());
                    transferwarehouseRepository.save((TransferwarehouseEntity) nextHop.getHop());
                    break;
                default:
            }
            warehouseNextHopsRepository.save(nextHop);
        }
    }

    @Override
    public boolean importWarehouses(WarehouseEntity warehouseEntity) {
        if (!myValidator.validate(warehouseEntity)) {
            return false;
        }

        if (warehouseRepository.count() != 0) {
            System.out.println("CountBeforeDeleting: " + warehouseRepository.count());
            deleteWarehouses();
            System.out.println("CountAfterDeleting trucks: " + truckRepository.count());
            System.out.println("CountAfterDeleting warehouses: " + warehouseRepository.count());
            System.out.println("CountAfterDeleting transferwarehouses: " + transferwarehouseRepository.count());

        }

        saveNestedWarehouses(warehouseEntity.getNextHops());
        geoCoordinateRepository.save(warehouseEntity.getLocationCoordinates());
        warehouseRepository.save(warehouseEntity);
        System.out.println("CountAfterSaving: " + warehouseRepository.count());

        return true;
    }
}

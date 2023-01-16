package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLDataNotFoundException;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.BLValidationException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

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

    public void deleteWarehouses() throws BLException {
        try {
            warehouseRepository.deleteAllWarehouseEntityNextHops();
            warehouseNextHopsRepository.deleteAllWarehouseNextHops();
            warehouseRepository.deleteAllWarehouses();
            truckRepository.deleteAll();
            transferwarehouseRepository.deleteAll();
        } catch (DataAccessException e) {
            log.error("There was an error connecting to the Database" + e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }
    }

    @Override
    public WarehouseEntity exportWarehouses() throws BLException {

        WarehouseEntity warehouseEntity;

        try {
            warehouseEntity = warehouseRepository.findByLevel(0);

        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

        if (warehouseEntity == null) {
            throw new BLDataNotFoundException(null, "No hierarchy loaded yet.");
        }

        log.info("found hierarchy" );

        return warehouseEntity;

    }

    @Override
    public Object getWarehousebyCode(String code) throws BLException {
        Object warehouse = null;

        try {
            if (truckRepository.findByCode(code) != null) {
                warehouse = truckRepository.findByCode(code);
                log.info("Code belongs to truck" );

            } else if (warehouseRepository.findByCode(code) != null) {
                warehouse = warehouseRepository.findByCode(code);
                log.info("Code belongs to warehouse" );
            } else if (transferwarehouseRepository.findByCode(code) != null) {
                warehouse = transferwarehouseRepository.findByCode(code);
                log.info("Code belongs to transfer warehouse" );

            } else {
                log.error("Could not find hop with the given Code");
                throw new BLDataNotFoundException(null, "No hop with the specified id could be found.");
            }
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }
        return warehouse;
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


    public void saveWarehouseHierarchy(WarehouseEntity warehouseEntity) throws BLException {

        try {
            saveNestedWarehouses(warehouseEntity.getNextHops());
            geoCoordinateRepository.save(warehouseEntity.getLocationCoordinates());
            warehouseRepository.save(warehouseEntity);

        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }
        log.info("Succesfully saved hierarchy ");

    }

    @Override
    public void importWarehouses(WarehouseEntity warehouseEntity) throws BLException {
        log.info("importing Warehouses ");

        try {
            myValidator.validate(warehouseEntity);
        } catch (BLValidationException e) {
            log.error(e.getMessage());
            throw new BLValidationException(e, e.getMessage());
        }

        try {
            if (warehouseRepository.count() != 0) {
                log.info("Count Before Deleting: " + warehouseRepository.count());
                try {
                    deleteWarehouses();
                    log.info("Count After Deleting: " + warehouseRepository.count());

                } catch (DataAccessException e) {
                    log.error(e.getMessage());
                    throw new BLException(e, "There was an error connecting to the Database");
                }
            }

        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

        try {
            saveWarehouseHierarchy(warehouseEntity);
            log.info("Count After saving new  hierarchy: " + warehouseRepository.count());

        } catch (DataAccessException e) {
            log.error(e.getMessage());
            log.error(e.getMessage());
            throw new BLException(e, "There was an error connecting to the Database");
        }

    }
}

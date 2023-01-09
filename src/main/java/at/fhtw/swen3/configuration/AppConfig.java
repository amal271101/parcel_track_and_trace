package at.fhtw.swen3.configuration;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.vaildation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ParcelServiceImpl parcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository, Validator validator, TruckRepository truckRepository, WarehouseRepository warehouseRepository) {
        return new ParcelServiceImpl(parcelRepository, recipientRepository, validator, truckRepository, warehouseRepository);
    }

    @Bean
    public WarehouseServiceImpl warehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, Validator validator, HopRepository hopRepository, GeoCoordinateRespository geoCoordinateRespository, TruckRepository truckRepository, TransferwarehouseRepository transferwarehouseRepository) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, validator, hopRepository,geoCoordinateRespository,truckRepository, transferwarehouseRepository);
    }


}
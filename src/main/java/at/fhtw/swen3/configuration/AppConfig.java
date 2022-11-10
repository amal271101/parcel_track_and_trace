package at.fhtw.swen3.configuration;

import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.vaildation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ParcelServiceImpl parcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository, Validator validator) {
        return new ParcelServiceImpl(parcelRepository, recipientRepository, validator);
    }

    @Bean
    public WarehouseServiceImpl warehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, Validator validator) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, validator);
    }


}

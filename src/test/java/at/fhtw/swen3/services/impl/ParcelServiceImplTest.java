package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.ParcelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParcelServiceImplTest {
    @Autowired
    private  ParcelService parcelService;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private TruckRepository truckRepository;




    @Test
    public void test(){
        TruckEntity truckEntity = truckRepository.findById(31564);
        WarehouseEntity warehouseEntity = warehouseRepository.findByLevel(0);
       // TruckEntity truckEntityB = truckRepository.findByCode("SBTA049");
       parcelService.findParent(warehouseEntity,truckEntity);
      //  assertNotNull( parcelService.findParentWarehouse(warehouseEntity,truckEntity));

    }

    /*@Test
    public void createParcelTest() {
        ParcelEntity parcelEntity = new ParcelEntity();

        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Landstraße 28a");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("A-1230");
        recipientEntity.setName("Amal");
        recipientEntity.setCountry("Austria");

        RecipientEntity senderEntity = new RecipientEntity();
        senderEntity.setStreet("Landstraße 28a");
        senderEntity.setCity("Cairo");
        senderEntity.setPostalCode("A-5635");
        senderEntity.setName("Marim");
        senderEntity.setCountry("Germany");

        parcelEntity.setSender(senderEntity);
        parcelEntity.setRecipient(recipientEntity);
        parcelEntity.setWeight(34f);
      ///  assertTrue( parcelService.createParcel(parcelEntity));

        parcelRepository.delete(parcelEntity);
        recipientRepository.delete(parcelEntity.getRecipient());
        recipientRepository.delete(parcelEntity.getSender());
    }*/

}
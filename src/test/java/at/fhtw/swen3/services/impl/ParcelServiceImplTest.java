package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ParcelServiceImplTest {
    @Autowired
    private  ParcelService parcelService;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

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
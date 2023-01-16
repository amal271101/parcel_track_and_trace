package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

public class ParcelRepositoryTest {
    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Test
    void saveAndDeleteParcelEntity() {

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);

        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Landstraße 27a");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("A-1210");
        recipientEntity.setName("Amal");
        recipientEntity.setCountry("Austria");

        RecipientEntity senderEntity = new RecipientEntity();
        senderEntity.setStreet("Landstraße 27a");
        senderEntity.setCity("Cairo");
        senderEntity.setPostalCode("A-5635");
        senderEntity.setName("Firas");
        senderEntity.setCountry("Egypt");

        parcelEntity.setSender(senderEntity);
        parcelEntity.setRecipient(recipientEntity);

        parcelEntity.setTrackingId("PYJRB4HZ6");
        parcelEntity.setWeight(34f);

        HopArrivalEntity hop = new HopArrivalEntity();
        hop.setDateTime(OffsetDateTime.now());
        hop.setCode("GEPU914");
        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        visitedHops.add(hop);
        parcelEntity.setFutureHops(visitedHops);
        parcelEntity.setVisitedHops(visitedHops);

        recipientRepository.save(parcelEntity.getRecipient());
        recipientRepository.save(parcelEntity.getSender());
        parcelRepository.save(parcelEntity);
        assertEquals(parcelRepository.findByTrackingId(parcelEntity.getTrackingId()).getTrackingId(),parcelEntity.getTrackingId());
        parcelRepository.delete(parcelEntity);
        recipientRepository.delete(senderEntity);
        recipientRepository.delete(recipientEntity);
        assertNull(parcelRepository.findByTrackingId(parcelEntity.getTrackingId()));

    }

}
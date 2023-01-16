package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.services.BLDataNotFoundException;
import at.fhtw.swen3.services.BLException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ParcelServiceImplTest {

@Autowired
ParcelServiceImpl parcelService;

    @Test
    public void testSubmitParcelThrowsBLException() {

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);

        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Landstraße 27a");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("1210");
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

        Throwable exception = assertThrows(BLException.class, () -> parcelService.submitParcel(parcelEntity));
        assertEquals("The address of sender or receiver was not found." , exception.getMessage());
    }

    @Test
    public void testReportDeliveryThrowsBLException() {
        Throwable exception = assertThrows(BLDataNotFoundException.class, () -> parcelService.reportParcelDelivery("OLMNUB2EF"));
        assertEquals("Parcel does not exist with this tracking ID." , exception.getMessage());
    }








}
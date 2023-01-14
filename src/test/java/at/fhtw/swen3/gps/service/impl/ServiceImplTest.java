package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.BLValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceImplTest {
    @Test
    public void encodingTest() throws BLValidationException {
        GeoEncodingServiceImpl service = new GeoEncodingServiceImpl();

        RecipientEntity recipient = new RecipientEntity();
        recipient.setStreet("Donaufelderstraße 99");
        recipient.setCity("Vienna");
        recipient.setPostalCode("1210");
        recipient.setCountry("Austria");

        assertEquals( service.encodeAddress(recipient).getLon(), 16.4199332);
        assertEquals( service.encodeAddress(recipient).getLat(), 48.2532887);
    }
}
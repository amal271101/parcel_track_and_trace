package Controller;

import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.controller.rest.ParcelApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelApiControllerTest {
    private ParcelApiController parcelController= new ParcelApiController(null, null);

   @Test
    void reportParcelDeliveryTestFail() {
        ResponseEntity<Void> responseEntity = parcelController.reportParcelDelivery("null");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }

    @Test
    void reportParcelDeliveryTestSuccess() {
        ResponseEntity<Void> responseEntity = parcelController.reportParcelDelivery("A3Z6F7B6N");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.CREATED).getStatusCode());
    }


    @Test
    void reportParcelHopTestFail() {
        ResponseEntity<Void> responseEntity = parcelController.reportParcelHop("A3Z6F7B6N", "ABVF9");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }
    @Test
    void trackParcelTestFail() {
        ResponseEntity<TrackingInformation> responseEntity = parcelController.trackParcel("parcelDto");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());

    }

    @Test
    void trackParcelTestSuccess() {
        ResponseEntity<TrackingInformation> responseEntity = parcelController.trackParcel("A3Z6F7B6N");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.CREATED).getStatusCode());

    }
    @Test
    void transitionParcelTestFail(){
        Float weight = 0.45f;
        Recipient recipientDto = new Recipient();
        recipientDto.setStreet("Donaufelder Street");
        recipientDto.setCity("Vienna");
        recipientDto.setPostalCode("1210");
        recipientDto.setName("Amal");
        recipientDto.setCountry("Austria");

        Recipient senderDto = new Recipient();
        senderDto.setStreet("Heliopolis Street");
        senderDto.setCity("Cairo");
        senderDto.setPostalCode("3333");
        senderDto.setName("Firas");
        senderDto.setCountry("Egypt");

        Parcel parcelDto = new Parcel();

        parcelDto.recipient(recipientDto);
        parcelDto.sender(senderDto);
        parcelDto.weight(weight);
        ResponseEntity<NewParcelInfo> responseEntity = parcelController.transitionParcel("A3Z6F7B6N",parcelDto);
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());

    }
}

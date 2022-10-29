package Controller;

import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.impl.ParcelApiController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelApiControllerTest {
    @Test
    void reportParcelDeliveryTestFail() {
        NativeWebRequest nativeWebRequest = null;
        ParcelApiController parcelApiController = new ParcelApiController(nativeWebRequest);
        ResponseEntity<Void> responseEntity = parcelApiController.reportParcelDelivery(null);
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }

    @Test
    void reportParcelDeliveryTestSuccess() {
        NativeWebRequest nativeWebRequest = null;
        ParcelApiController parcelApiController = new ParcelApiController(nativeWebRequest);
        ResponseEntity<Void> responseEntity = parcelApiController.reportParcelDelivery("A3Z6F7B6N");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.CREATED).getStatusCode());
    }


    @Test
    void reportParcelHopTestFail() {
        NativeWebRequest nativeWebRequest = null;
        ParcelApiController parcelApiController = new ParcelApiController(nativeWebRequest);
        ResponseEntity<Void> responseEntity = parcelApiController.reportParcelHop("A3Z6F7B6N", "ABVF9");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }

    @Test
    void submitParcelTestFail() {

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

        NativeWebRequest nativeWebRequest = null;
        ParcelApiController parcelApiController = new ParcelApiController(nativeWebRequest);
        ResponseEntity<NewParcelInfo> responseEntity = parcelApiController.submitParcel(parcelDto);
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());
    }


    @Test
    void trackParcelTestFail() {
        NativeWebRequest nativeWebRequest = null;
        ParcelApiController parcelApiController = new ParcelApiController(nativeWebRequest);
        ResponseEntity<TrackingInformation> responseEntity = parcelApiController.trackParcel("parcelDto");
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());

    }

    @Test
    void trackParcelTestSuccess() {
        NativeWebRequest nativeWebRequest = null;
        ParcelApiController parcelApiController = new ParcelApiController(nativeWebRequest);
        ResponseEntity<TrackingInformation> responseEntity = parcelApiController.trackParcel("A3Z6F7B6N");
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
        NativeWebRequest nativeWebRequest = null;
        ParcelApiController parcelApiController = new ParcelApiController(nativeWebRequest);
        ResponseEntity<NewParcelInfo> responseEntity = parcelApiController.transitionParcel("A3Z6F7B6N",parcelDto);
        assertEquals(responseEntity.getStatusCode(), new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode());

    }
}

package at.fhtw.swen3.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class ParcelApiControllerTest {
    @Autowired
   ParcelApiController parcelApiController;

        @Test
        public void trackParcelThrowsException() {
            Throwable exception = assertThrows(Exception.class, () -> parcelApiController.trackParcel("OLMNNb2EF"));
            assertEquals("trackParcel.trackingId: muss auf Ausdruck \"^[A-Z0-9]{9}$\" passen" , exception.getMessage());
        }


}
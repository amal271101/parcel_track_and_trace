package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParcelEntityMapper {
    @Test
    void dtoToEntity(){
        HopArrival hopDto = new HopArrival();
        hopDto.setDateTime(OffsetDateTime.now());
        hopDto.setCode("Code123");
        hopDto.setDescription("first hop");

        HopArrival hopDto2 = new HopArrival();
        hopDto2.setDateTime(OffsetDateTime.now());
        hopDto2.setCode("Code123");
        hopDto2.setDescription("first hop");

        List <HopArrival> visitedHopsDto= new ArrayList<>();
        visitedHopsDto.add(hopDto);
        visitedHopsDto.add(hopDto2);

        String trakingIdDto="id22312";

        NewParcelInfo newParcelInfodto = new NewParcelInfo();
        newParcelInfodto.setTrackingId(trakingIdDto);

        TrackingInformation trackingInformationdto = new TrackingInformation();
        trackingInformationdto.setVisitedHops(visitedHopsDto);
        trackingInformationdto.setState(TrackingInformation.StateEnum.PICKUP);
        trackingInformationdto.setFutureHops(visitedHopsDto);

        Float weight = Float.valueOf(50);

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

        ParcelEntity parcel = ParcelMapper.INSTANCE.dtosToEntity( parcelDto,trackingInformationdto,newParcelInfodto);

        assertEquals(parcel.getWeight(),weight);

    }

    @Test
    void TrackingInformationtoParcelEntity() {
        HopArrival hopDto = new HopArrival();
        hopDto.setDateTime(OffsetDateTime.now());
        hopDto.setCode("Code123");
        hopDto.setDescription("first hop");

        HopArrival hopDto2 = new HopArrival();
        hopDto2.setDateTime(OffsetDateTime.now());
        hopDto2.setCode("Code123");
        hopDto2.setDescription("first hop");

        List<HopArrival> visitedHopsDto = new ArrayList<>();
        visitedHopsDto.add(hopDto);
        visitedHopsDto.add(hopDto2);


        TrackingInformation trackingInformationdto = new TrackingInformation();
        trackingInformationdto.setVisitedHops(visitedHopsDto);
        trackingInformationdto.setState(TrackingInformation.StateEnum.PICKUP);
        trackingInformationdto.setFutureHops(visitedHopsDto);



        ParcelEntity parcel = ParcelMapper.INSTANCE.TrackingInformationToParcelEntity(trackingInformationdto);

        assertEquals(parcel.getVisitedHops().get(0).getCode(), visitedHopsDto.get(0).getCode());
        assertEquals(parcel.getFutureHops().get(1).getDescription(), visitedHopsDto.get(1).getDescription());
        assertEquals(parcel.getState().getValue(), TrackingInformation.StateEnum.PICKUP.getValue());

        assertNull(parcel.getWeight());
        assertNull(parcel.getTrackingId());
        assertNull(parcel.getRecipient());
        assertNull(parcel.getSender());

    }




}

package Mapper;

import at.fhtw.swen3.persistence.entity.Parcel;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelEntityMapper {


    @Test
    void dtoToEntity(){
        HopArrivalDto hopDto = new HopArrivalDto("Code123","first hop",OffsetDateTime.now());
        HopArrivalDto hopDto2 = new HopArrivalDto("Code33","second hop",OffsetDateTime.now());

        List <HopArrivalDto> visitedHopsDto= new ArrayList<>();
        visitedHopsDto.add(hopDto);
        visitedHopsDto.add(hopDto2);

        String trakingIdDto="id22312";

        NewParcelInfoDto newParcelInfodto = new NewParcelInfoDto(trakingIdDto);
        TrackingInformationDto trackingInformation = new TrackingInformationDto(TrackingInformationDto.StateEnum.PICKUP ,visitedHopsDto,visitedHopsDto);

        Float weight = Float.valueOf(50);
        RecipientDto recipient= new RecipientDto("Amal","Donaufelder Street","1210","Vienna","Austria");

        RecipientDto sender = new RecipientDto("Firas","Heliopolis Street","333","Cairo","Egypt");

        ParcelDto parcelDto = new ParcelDto(weight,recipient,sender);

        Parcel parcel = at.fhtw.swen3.services.mapper.ParcelEntityMapper.INSTANCE.dtosToEntity( parcelDto,trackingInformation,newParcelInfodto);

        assertEquals(parcel.getWeight(),weight);

    }
}


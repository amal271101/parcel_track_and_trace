package Mapper;

import at.fhtw.swen3.persistence.entity.HopArrival;
import at.fhtw.swen3.persistence.entity.Parcel;
import at.fhtw.swen3.persistence.entity.Recipient;
import at.fhtw.swen3.persistence.entity.StateEnum;
import at.fhtw.swen3.services.dto.ParcelDto;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelDtoMapper {
    @Test
    void entityToDto() {
        HopArrival hop1 = new HopArrival("Code123", "first hop", OffsetDateTime.now());
        HopArrival hop2 = new HopArrival("Code33", "second hop", OffsetDateTime.now());

        List<HopArrival> visitedHops = new ArrayList<>();
        visitedHops.add(hop1);
        visitedHops.add(hop2);

        List<HopArrival> futureHops = new ArrayList<>();
        futureHops.add(hop1);
        futureHops.add(hop2);

        Recipient recipient = new Recipient("Amal", "Donaufelder Street", "1210", "Vienna", "Austria");
        Recipient sender = new Recipient("Firas", "Heliopolis Street", "333", "Cairo", "Egypt");

        String trackingId="id22312";
        Float weight = Float.valueOf(50);

        Parcel parcelEntity=new Parcel(weight,trackingId,sender,recipient, StateEnum.PICKUP,futureHops,visitedHops);


        ParcelDto parcelDto = at.fhtw.swen3.services.mapper.ParcelDtoMapper.INSTANCE.entityToDto(parcelEntity);

        assertEquals(parcelDto.getWeight(),parcelEntity.getWeight());
        assertEquals(parcelDto.getRecipient().getCity(),parcelEntity.getRecipient().getCity());

    }

}

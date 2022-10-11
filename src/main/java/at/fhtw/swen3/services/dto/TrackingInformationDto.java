package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.HopArrival;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TrackingInformationDto {

    public enum StateEnum{
        PICKUP("Pickup"),
        INTRANSPORT("InTransport"),
        INTRUCKDELIVERY("InTruckDelivery"),
        TRANSFERRED("Transferred"),
        DELIVERED("Delivered");

        private String value;
        StateEnum (String value){this.value = value;}
    }
    private StateEnum state;
    private List<HopArrivalDto> futureHops = new ArrayList<>();
    private List<HopArrivalDto> visitedHops = new ArrayList<>();

}

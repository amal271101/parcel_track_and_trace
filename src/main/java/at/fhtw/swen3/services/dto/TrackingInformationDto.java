package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.HopArrival;
import at.fhtw.swen3.persistence.TrackingInformation;

import java.util.ArrayList;
import java.util.List;

enum StateEnum {
    PICKUP("Pickup"),

    INTRANSPORT("InTransport"),

    INTRUCKDELIVERY("InTruckDelivery"),

    TRANSFERRED("Transferred"),

    DELIVERED("Delivered");

    private String value;

    StateEnum(String value) {
        this.value = value;
    }
}


public class TrackingInformationDto {
    private TrackingInformation.StateEnum state;
    private List<HopArrival> visitedHops = new ArrayList<>();

    private List<HopArrival> futureHops = new ArrayList<>();


    public List<HopArrival> getVisitedHops() {
        return visitedHops;
    }

    public void setVisitedHops(List<HopArrival> visitedHops) {
        this.visitedHops = visitedHops;
    }


    public List<HopArrival> getFutureHops() {
        return futureHops;
    }

    public void setFutureHops(List<HopArrival> futureHops) {
        this.futureHops = futureHops;
    }

    public TrackingInformation.StateEnum getState() {
        return state;
    }

    public void setState(TrackingInformation.StateEnum state) {
        this.state = state;
    }
}

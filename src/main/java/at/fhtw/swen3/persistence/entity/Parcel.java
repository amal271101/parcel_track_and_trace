package at.fhtw.swen3.persistence.entity;


import at.fhtw.swen3.persistence.TrackingInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Parcel {
    @Size(min = 0, message = "weight must be greater then or equal to 0")
    private float weight;

    private String trackingId;

    @NotNull(message = "sender cannot be null")
    private Recipient sender;

    @NotNull(message = "recipient cannot be null")
    private Recipient recipient;

    private StateEnum state;

    @NotNull(message = "futureHops cannot be null")
    private List<HopArrival> futureHops;

    @NotNull(message = "futureHops cannot be null")
    private List<HopArrival> visitedHops;
}

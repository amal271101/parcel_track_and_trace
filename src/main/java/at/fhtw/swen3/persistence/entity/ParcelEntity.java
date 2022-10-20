package at.fhtw.swen3.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParcelEntity {
   @Size(min = 0, message = "weight must be greater then or equal to 0")
    private Float weight;


   @Pattern(regexp = "^[A-Z0-9]{9}$")
   private String trackingId;

    @NotNull(message = "sender cannot be null")
    private RecipientEntity sender;

    @NotNull(message = "recipient cannot be null")
    private RecipientEntity recipient;

    private TrackingInformationEntity.StateEnumEntity state;

    @NotNull(message = "futureHops cannot be null")
    private List<HopArrivalEntity> futureHops;

    @NotNull(message = "visitedHops cannot be null")
    private List<HopArrivalEntity> visitedHops;
}

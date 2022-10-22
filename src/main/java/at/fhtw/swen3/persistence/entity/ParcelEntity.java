package at.fhtw.swen3.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParcelEntity {

    @Min(0)@Max(10) @NotNull(message = "weight must be between 1 and 10")
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

package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Entity
public class ParcelEntity {

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @NotNull(message = "weight must be between 1 and 10")
    private Float weight;

    @Id
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @ManyToOne
    @NotNull(message = "sender cannot be null")
    private RecipientEntity sender;

    @ManyToOne
    @NotNull(message = "recipient cannot be null")
    private RecipientEntity recipient;

    private TrackingInformationEntity.StateEnumEntity state;

    @OneToMany
    @NotNull(message = "futureHops cannot be null")
    private List<HopArrivalEntity> futureHops;

    @OneToMany
    @NotNull(message = "visitedHops cannot be null")
    private List<HopArrivalEntity> visitedHops;
}

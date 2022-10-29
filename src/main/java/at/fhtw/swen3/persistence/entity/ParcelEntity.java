package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Entity
public class ParcelEntity {

    @Column
    @DecimalMin("1.0")
    @NotNull(message = "weight must be between 1 and 10")
    private Float weight;

    @Column
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @ManyToOne
    @NotNull(message = "sender cannot be null")
    private RecipientEntity sender;

    @ManyToOne
    @NotNull(message = "recipient cannot be null")
    private RecipientEntity recipient;

    @Column
    private TrackingInformationEntity.StateEnumEntity state;

    @Column
    @OneToMany
    @NotNull(message = "futureHops cannot be null")
    private List<HopArrivalEntity> futureHops;

    @OneToMany
    @NotNull(message = "visitedHops cannot be null")
    private List<HopArrivalEntity> visitedHops;
    @Id
    private Long id;

}

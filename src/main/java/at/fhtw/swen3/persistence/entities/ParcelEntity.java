package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "parcel")
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
    @JoinColumn(name = "sender_id")
    private RecipientEntity sender;

    @ManyToOne
    @NotNull(message = "recipient cannot be null")
    @JoinColumn(name = "recipient_id")
    private RecipientEntity recipient;

    @Column
    @NotNull(message = "state cannot be null")
    private TrackingInformationEntity.StateEnumEntity state;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "futureHops cannot be null")
    private List<HopArrivalEntity> futureHops;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "visitedHops cannot be null")
    private List<HopArrivalEntity> visitedHops;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}

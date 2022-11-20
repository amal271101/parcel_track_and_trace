package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "hop")
public class HopEntity {

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;

    @Column
    private String hopType;

    @Column
    private String description;

    @Column
    private Integer processingDelayMins;

    @Column
    private String locationName;

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="geocoordinate_id", nullable=false)
    @NotNull
    private GeoCoordinateEntity locationCoordinates;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}

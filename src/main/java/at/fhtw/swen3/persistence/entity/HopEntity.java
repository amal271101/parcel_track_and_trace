package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
public class HopEntity {
    @Id
    @Column(nullable = false)

    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;


    private String hopType;


    private String description;

    private Integer processingDelayMins;

    private String locationName;

    @NotNull
    @OneToOne
    private GeoCoordinateEntity locationCoordinates;
}

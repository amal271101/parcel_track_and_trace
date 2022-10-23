package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private String code;


    private String hopType;


    private String description;

    private Integer processingDelayMins;

    private String locationName;

    @OneToOne
    private GeoCoordinateEntity locationCoordinates;
}

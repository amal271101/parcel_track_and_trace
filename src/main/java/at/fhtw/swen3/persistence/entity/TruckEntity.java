package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class TruckEntity extends HopEntity {
    @Column
    private String regionGeoJson;

    @Column
    private String numberPlate;

    @Column
    private String hopType;

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private String locationName;

    @Column
    private Integer processingDelayMins;

    @OneToOne
    private GeoCoordinateEntity locationCoordinates;


}

package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class TruckEntity extends HopEntity {
    private String regionGeoJson;

    private String numberPlate;

    private String hopType;

    private String code;

    private String description;

    private String locationName;

    private Integer processingDelayMins;

    @OneToOne
    private GeoCoordinateEntity locationCoordinates;


}

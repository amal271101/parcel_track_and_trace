package at.fhtw.swen3.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TruckEntity {
    private String regionGeoJson;

    private String numberPlate;

    private String hopType;

    private String code;

    private String description;

    private String locationName;


    private Integer processingDelayMins;

    private GeoCoordinateEntity locationCoordinates;







}

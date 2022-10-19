package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HopEntity {
    private String hopType;

    private String code;

    private String description;

    private Integer processingDelayMins;

    private String locationName;

    private GeoCoordinate locationCoordinates;
}

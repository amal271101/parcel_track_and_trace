package at.fhtw.swen3.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferwarehouseEntity {

    private String regionGeoJson;

    private String logisticsPartner;

    private String logisticsPartnerUrl;

    private String hopType;

    private String code;

    private String description;

    private Integer processingDelayMins;

    private String locationName;

    private GeoCoordinateEntity locationCoordinates;

}

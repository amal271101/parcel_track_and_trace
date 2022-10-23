package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class TransferwarehouseEntity extends HopEntity{

    private String regionGeoJson;

    private String logisticsPartner;

    private String logisticsPartnerUrl;

    private String hopType;

    private String code;

    private String description;

    private Integer processingDelayMins;

    private String locationName;

    @OneToOne
    private GeoCoordinateEntity locationCoordinates;

}

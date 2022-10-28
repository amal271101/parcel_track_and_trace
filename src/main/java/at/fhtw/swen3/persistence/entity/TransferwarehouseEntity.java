package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class TransferwarehouseEntity extends HopEntity{

    @Column
    private String regionGeoJson;

    @Column
    private String logisticsPartner;

    @Column
    private String logisticsPartnerUrl;

    @Column
    private String hopType;

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private Integer processingDelayMins;

    @Column
    private String locationName;

    @OneToOne
    private GeoCoordinateEntity locationCoordinates;

}

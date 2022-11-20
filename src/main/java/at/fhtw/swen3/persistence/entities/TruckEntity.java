package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "truck")
public class TruckEntity extends HopEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Entity
public class WarehouseEntity extends HopEntity{

    private Integer level;

    @NotEmpty(message = "nextHops cannot be null")
    @OneToMany
    private List<WarehouseNextHopsEntity> nextHops;

    private String hopType;

    private String code;

    @Pattern(regexp = "[A-Za-z1-9-/ öÖÜüÄäß]+")
    private String description;

    private Integer processingDelayMins;

    private String locationName;

    @OneToOne
    private GeoCoordinateEntity locationCoordinates;


}

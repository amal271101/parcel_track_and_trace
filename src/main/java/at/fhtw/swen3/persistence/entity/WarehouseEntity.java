package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    @Column
    private Integer level;

    @Column
    @NotNull
    @NotEmpty(message = "nextHops cannot be null")
    @OneToMany
    private List<WarehouseNextHopsEntity> nextHops;

    @Column
    private String hopType;

    @Column
    private String code;

    @Column
    @Pattern(regexp = "[A-Za-z1-9-/ öÖÜüÄäß]+")
    private String description;

    @Column
    private Integer processingDelayMins;

    @Column
    private String locationName;

    @OneToOne
    private GeoCoordinateEntity locationCoordinates;

}

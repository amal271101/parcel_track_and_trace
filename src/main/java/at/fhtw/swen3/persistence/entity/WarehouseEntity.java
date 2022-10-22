package at.fhtw.swen3.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WarehouseEntity {

    private Integer level;

    @NotEmpty(message = "nextHops cannot be null")
    private List<WarehouseNextHopsEntity> nextHops;

    private String hopType;

    private String code;

    @Pattern(regexp = "[A-Za-z1-9-/ öÖÜüÄäß]+")
    private String description;

    private Integer processingDelayMins;

    private String locationName;

    private GeoCoordinateEntity locationCoordinates;


}

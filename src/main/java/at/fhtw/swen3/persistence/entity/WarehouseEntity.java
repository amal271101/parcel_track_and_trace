package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class WarehouseEntity {
    private Integer level;

    private List<WarehouseNextHops> nextHops;

    private String hopType;


    private String code;


    @Pattern(regexp = "[A-Za-z1-9-/ öÖÜüÄäß]+")
    private String description;

    private Integer processingDelayMins;

    private String locationName;

    private GeoCoordinateEntity locationCoordinates;


}

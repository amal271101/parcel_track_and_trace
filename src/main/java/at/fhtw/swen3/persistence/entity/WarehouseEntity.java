package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class WarehouseEntity {
    private Integer level;

    private List<WarehouseNextHops> nextHops;

    private String hopType;

    private String code;

    private String description;

    private Integer processingDelayMins;

    private String locationName;

    private GeoCoordinateEntity locationCoordinates;

    /*
    *
    *
    *   public Warehouse hopType(String hopType) {
    super.setHopType(hopType);
    return this;
  }

  public Warehouse code(String code) {
    super.setCode(code);
    return this;
  }

  public Warehouse description(String description) {
    super.setDescription(description);
    return this;
  }

  public Warehouse processingDelayMins(Integer processingDelayMins) {
    super.setProcessingDelayMins(processingDelayMins);
    return this;
  }

  public Warehouse locationName(String locationName) {
    super.setLocationName(locationName);
    return this;
  }

  public Warehouse locationCoordinates(GeoCoordinate locationCoordinates) {
    super.setLocationCoordinates(locationCoordinates);
    return this;
  }*/
}

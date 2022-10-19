package at.fhtw.swen3.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TruckEntity {
    private String regionGeoJson;

    private String numberPlate;

    private String hopType;

    private String code;

    private String description;

    private String locationName;


    private Integer processingDelayMins;

    private GeoCoordinateEntity locationCoordinates;

    /*
    *   public Truck hopType(String hopType) {
    super.setHopType(hopType);
    return this;
  }

  public Truck code(String code) {
    super.setCode(code);
    return this;
  }

  public Truck description(String description) {
    super.setDescription(description);
    return this;
  }

  public Truck processingDelayMins(Integer processingDelayMins) {
    super.setProcessingDelayMins(processingDelayMins);
    return this;
  }

  public Truck locationName(String locationName) {
    super.setLocationName(locationName);
    return this;
  }

  public Truck locationCoordinates(GeoCoordinate locationCoordinates) {
    super.setLocationCoordinates(locationCoordinates);
    return this;
  }*/




}

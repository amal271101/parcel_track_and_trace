package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Entity
public class WarehouseEntity extends HopEntity{
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

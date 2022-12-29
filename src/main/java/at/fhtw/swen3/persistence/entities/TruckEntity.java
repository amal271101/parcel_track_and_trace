package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

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
}

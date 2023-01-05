package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TransferwarehouseEntity extends HopEntity{

    @Column
    private Geometry regionGeoJson;

    @Column
    private String logisticsPartner;

    @Column
    private String logisticsPartnerUrl;

}

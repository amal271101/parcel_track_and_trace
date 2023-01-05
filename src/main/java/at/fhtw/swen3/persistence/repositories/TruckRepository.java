package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<TruckEntity, String> {
  /*  @Query(value = "SELECT truckentity " +
            "FROM truck truckentity " +
            "JOIN geo_coordinate gc ON truckentity.geocoordinate_id = gc.id " +
            "ORDER BY ST_Distance(ST_MakePoint(gc.lon, gc.lat),:targetPoint ) " +
            "LIMIT 1", nativeQuery = true)
    TruckEntity findNearestHop(@Param("targetPoint") Point point);
*/

    TruckEntity findByCode(String code);

}







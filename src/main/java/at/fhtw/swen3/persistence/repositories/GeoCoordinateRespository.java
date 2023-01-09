package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;
import java.sql.Types;

@Repository
public interface GeoCoordinateRespository extends JpaRepository<GeoCoordinateEntity,Long> {
    GeoCoordinateEntity findByLat(Double lat);
   /* @Query(value = "SELECT gc " +
            "FROM geo_coordinate gc " +
            "ORDER BY ST_ShortestLine(ST_MakePoint(gc.lon, gc.lat),:targetPoint ) " +
            "LIMIT 1", nativeQuery = true)
    GeoCoordinateEntity findNearestHop(@Param("targetPoint") Point point);


    @Query(value = "SELECT ST_MakePoint(gc.lon, gc.lat) as point " +
            "FROM geo_coordinate gc " +
            "WHERE gc.id = :id", nativeQuery = true)

    Point findPointById(@Param("id") Long id);*/

}

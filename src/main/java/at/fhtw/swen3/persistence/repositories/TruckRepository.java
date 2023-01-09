package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<TruckEntity, String> {
    @Query(value = "SELECT *" +
            "FROM truck truckentity\n" +
            "         JOIN geo_coordinate gc ON truckentity.geocoordinate_id = gc.id\n" +
            "ORDER BY ST_Distance(ST_MakePoint(CAST(gc.lon AS float8), CAST(gc.lat AS float8)), ST_MakePoint(:lon, :lat))\n" +
            "LIMIT 1", nativeQuery = true)
    TruckEntity findNearestHop(@Param("lat") Double lat, @Param("lon") Double lon);

    TruckEntity findByCode(String code);

    TruckEntity findById(long id);

}







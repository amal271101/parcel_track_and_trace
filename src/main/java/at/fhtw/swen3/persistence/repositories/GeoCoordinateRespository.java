package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoCoordinateRespository extends JpaRepository<GeoCoordinateEntity,Long> {
    GeoCoordinateEntity findByLat(Double lat);

}

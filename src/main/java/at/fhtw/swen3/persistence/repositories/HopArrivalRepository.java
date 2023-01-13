package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HopArrivalRepository extends JpaRepository<HopArrivalEntity, Long> {
        // your methods {
    HopArrivalEntity findByCode(String Code);


    @Query(value = "SELECT *" +
            "FROM  hop_arrival WHERE code = :hopCode " +
            " LIMIT 1", nativeQuery = true)
    HopArrivalEntity findFirstByCode(@Param("hopCode")String code);
}

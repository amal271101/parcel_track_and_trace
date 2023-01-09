package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
 WarehouseEntity findByCode(String code);

 WarehouseEntity findByLevel(int level);

// @Query("SELECT w.id FROM WarehouseEntity w JOIN w.nextHops n WHERE n.id = :nextHopsId")
 //int getid(@Param("hopId") Long hopId);
 //WarehouseEntity findByNextHops
 //String hql = "SELECT w.id FROM WarehouseEntity w JOIN w.nextHops n WHERE n.hop.id = :hopId";

}
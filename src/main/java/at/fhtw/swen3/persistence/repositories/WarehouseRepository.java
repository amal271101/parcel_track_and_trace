package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
 WarehouseEntity findByCode(String code);

 WarehouseEntity findByLevel(int level);


@Modifying
@Transactional
@Query(value = "DELETE FROM warehouse_entity", nativeQuery = true)
void deleteAllWarehouses();

 @Modifying
 @Transactional
 @Query(value = "DELETE FROM warehouse_entity_next_hops", nativeQuery = true)
 void deleteAllWarehouseEntityNextHops();
}
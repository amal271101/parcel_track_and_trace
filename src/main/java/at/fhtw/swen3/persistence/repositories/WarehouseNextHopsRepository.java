package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WarehouseNextHopsRepository extends JpaRepository<WarehouseNextHopsEntity, Integer> {
    WarehouseNextHopsEntity findByTraveltimeMins(int mins);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ware_house_next_hops", nativeQuery = true)
    void deleteAllWarehouseNextHops();
}

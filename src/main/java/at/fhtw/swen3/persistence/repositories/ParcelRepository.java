package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    ParcelEntity findByTrackingId(String trackingId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE parcel  SET state = 4 WHERE tracking_id = :trackingid",nativeQuery = true)
    void setStateToDelivered(@Param("trackingid") String trackingID);


}

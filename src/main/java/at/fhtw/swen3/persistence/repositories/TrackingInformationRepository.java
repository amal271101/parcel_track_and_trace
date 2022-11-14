package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingInformationRepository extends JpaRepository<TrackingInformationEntity, Long> {
TrackingInformationEntity findByState(TrackingInformationEntity.StateEnumEntity stateEnumEntity);
}

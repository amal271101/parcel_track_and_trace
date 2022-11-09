package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.NewParcelInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewParcelInfoRespository extends JpaRepository<NewParcelInfoEntity, Integer> {
}

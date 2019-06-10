package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCombinationCutPosition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCombinationCutPosition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCombinationCutPositionRepository extends JpaRepository<MCombinationCutPosition, Long>, JpaSpecificationExecutor<MCombinationCutPosition> {

}

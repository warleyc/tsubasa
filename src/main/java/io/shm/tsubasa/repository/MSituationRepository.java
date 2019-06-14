package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MSituation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MSituation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MSituationRepository extends JpaRepository<MSituation, Long>, JpaSpecificationExecutor<MSituation> {

}

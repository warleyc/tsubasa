package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMatchFormation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMatchFormation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMatchFormationRepository extends JpaRepository<MMatchFormation, Long>, JpaSpecificationExecutor<MMatchFormation> {

}

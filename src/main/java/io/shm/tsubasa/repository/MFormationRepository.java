package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MFormation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MFormation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MFormationRepository extends JpaRepository<MFormation, Long>, JpaSpecificationExecutor<MFormation> {

}

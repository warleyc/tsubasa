package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MRivalEncountCutin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MRivalEncountCutin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MRivalEncountCutinRepository extends JpaRepository<MRivalEncountCutin, Long>, JpaSpecificationExecutor<MRivalEncountCutin> {

}

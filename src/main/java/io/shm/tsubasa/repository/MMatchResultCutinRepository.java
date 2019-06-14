package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMatchResultCutin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMatchResultCutin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMatchResultCutinRepository extends JpaRepository<MMatchResultCutin, Long>, JpaSpecificationExecutor<MMatchResultCutin> {

}

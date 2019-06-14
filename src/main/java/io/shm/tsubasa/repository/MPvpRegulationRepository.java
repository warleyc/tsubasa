package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpRegulation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpRegulation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpRegulationRepository extends JpaRepository<MPvpRegulation, Long>, JpaSpecificationExecutor<MPvpRegulation> {

}

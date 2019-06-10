package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDistributeCardParameterStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDistributeCardParameterStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDistributeCardParameterStepRepository extends JpaRepository<MDistributeCardParameterStep, Long>, JpaSpecificationExecutor<MDistributeCardParameterStep> {

}

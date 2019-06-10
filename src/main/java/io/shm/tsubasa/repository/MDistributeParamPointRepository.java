package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDistributeParamPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDistributeParamPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDistributeParamPointRepository extends JpaRepository<MDistributeParamPoint, Long>, JpaSpecificationExecutor<MDistributeParamPoint> {

}

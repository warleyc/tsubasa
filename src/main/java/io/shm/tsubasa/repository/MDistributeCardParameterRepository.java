package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDistributeCardParameter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDistributeCardParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDistributeCardParameterRepository extends JpaRepository<MDistributeCardParameter, Long>, JpaSpecificationExecutor<MDistributeCardParameter> {

}

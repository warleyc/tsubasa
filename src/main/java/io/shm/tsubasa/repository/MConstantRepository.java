package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MConstant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MConstant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MConstantRepository extends JpaRepository<MConstant, Long>, JpaSpecificationExecutor<MConstant> {

}

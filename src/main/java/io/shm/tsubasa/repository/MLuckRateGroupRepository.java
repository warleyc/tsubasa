package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLuckRateGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLuckRateGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLuckRateGroupRepository extends JpaRepository<MLuckRateGroup, Long>, JpaSpecificationExecutor<MLuckRateGroup> {

}

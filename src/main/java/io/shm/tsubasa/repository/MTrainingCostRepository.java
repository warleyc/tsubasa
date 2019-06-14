package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTrainingCost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTrainingCost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTrainingCostRepository extends JpaRepository<MTrainingCost, Long>, JpaSpecificationExecutor<MTrainingCost> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMissionReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMissionReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMissionRewardRepository extends JpaRepository<MMissionReward, Long>, JpaSpecificationExecutor<MMissionReward> {

}

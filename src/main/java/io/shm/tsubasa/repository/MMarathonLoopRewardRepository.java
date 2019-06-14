package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonLoopReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonLoopReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonLoopRewardRepository extends JpaRepository<MMarathonLoopReward, Long>, JpaSpecificationExecutor<MMarathonLoopReward> {

}

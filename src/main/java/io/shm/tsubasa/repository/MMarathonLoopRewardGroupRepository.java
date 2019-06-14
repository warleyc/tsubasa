package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonLoopRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonLoopRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonLoopRewardGroupRepository extends JpaRepository<MMarathonLoopRewardGroup, Long>, JpaSpecificationExecutor<MMarathonLoopRewardGroup> {

}

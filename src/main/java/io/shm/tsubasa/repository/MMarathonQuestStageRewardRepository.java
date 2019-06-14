package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonQuestStageRewardRepository extends JpaRepository<MMarathonQuestStageReward, Long>, JpaSpecificationExecutor<MMarathonQuestStageReward> {

}

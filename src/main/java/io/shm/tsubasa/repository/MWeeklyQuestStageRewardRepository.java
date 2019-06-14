package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MWeeklyQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MWeeklyQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MWeeklyQuestStageRewardRepository extends JpaRepository<MWeeklyQuestStageReward, Long>, JpaSpecificationExecutor<MWeeklyQuestStageReward> {

}

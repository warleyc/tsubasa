package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestGoalReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestGoalReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestGoalRewardRepository extends JpaRepository<MQuestGoalReward, Long>, JpaSpecificationExecutor<MQuestGoalReward> {

}

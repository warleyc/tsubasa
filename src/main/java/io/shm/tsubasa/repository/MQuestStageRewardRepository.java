package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestStageRewardRepository extends JpaRepository<MQuestStageReward, Long>, JpaSpecificationExecutor<MQuestStageReward> {

}

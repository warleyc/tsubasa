package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLuckWeeklyQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLuckWeeklyQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLuckWeeklyQuestStageRewardRepository extends JpaRepository<MLuckWeeklyQuestStageReward, Long>, JpaSpecificationExecutor<MLuckWeeklyQuestStageReward> {

}

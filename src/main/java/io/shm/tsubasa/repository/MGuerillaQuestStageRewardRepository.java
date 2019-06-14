package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuerillaQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuerillaQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuerillaQuestStageRewardRepository extends JpaRepository<MGuerillaQuestStageReward, Long>, JpaSpecificationExecutor<MGuerillaQuestStageReward> {

}

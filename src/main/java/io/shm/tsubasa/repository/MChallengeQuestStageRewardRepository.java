package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MChallengeQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MChallengeQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MChallengeQuestStageRewardRepository extends JpaRepository<MChallengeQuestStageReward, Long>, JpaSpecificationExecutor<MChallengeQuestStageReward> {

}

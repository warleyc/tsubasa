package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MChallengeQuestAchievementReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MChallengeQuestAchievementReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MChallengeQuestAchievementRewardRepository extends JpaRepository<MChallengeQuestAchievementReward, Long>, JpaSpecificationExecutor<MChallengeQuestAchievementReward> {

}

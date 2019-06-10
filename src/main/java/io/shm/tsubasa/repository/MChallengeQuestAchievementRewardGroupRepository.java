package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MChallengeQuestAchievementRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MChallengeQuestAchievementRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MChallengeQuestAchievementRewardGroupRepository extends JpaRepository<MChallengeQuestAchievementRewardGroup, Long>, JpaSpecificationExecutor<MChallengeQuestAchievementRewardGroup> {

}

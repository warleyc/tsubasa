package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonAchievementReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonAchievementReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonAchievementRewardRepository extends JpaRepository<MMarathonAchievementReward, Long>, JpaSpecificationExecutor<MMarathonAchievementReward> {

}

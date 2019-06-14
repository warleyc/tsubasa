package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonAchievementRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonAchievementRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonAchievementRewardGroupRepository extends JpaRepository<MMarathonAchievementRewardGroup, Long>, JpaSpecificationExecutor<MMarathonAchievementRewardGroup> {

}

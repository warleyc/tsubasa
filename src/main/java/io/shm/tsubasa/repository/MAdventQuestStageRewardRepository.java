package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAdventQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAdventQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAdventQuestStageRewardRepository extends JpaRepository<MAdventQuestStageReward, Long>, JpaSpecificationExecutor<MAdventQuestStageReward> {

}

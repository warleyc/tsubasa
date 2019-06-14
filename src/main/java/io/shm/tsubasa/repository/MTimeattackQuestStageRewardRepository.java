package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTimeattackQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTimeattackQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTimeattackQuestStageRewardRepository extends JpaRepository<MTimeattackQuestStageReward, Long>, JpaSpecificationExecutor<MTimeattackQuestStageReward> {

}

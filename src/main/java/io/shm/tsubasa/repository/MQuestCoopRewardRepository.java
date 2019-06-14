package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestCoopReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestCoopReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestCoopRewardRepository extends JpaRepository<MQuestCoopReward, Long>, JpaSpecificationExecutor<MQuestCoopReward> {

}

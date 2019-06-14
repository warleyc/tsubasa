package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestSpecialReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestSpecialReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestSpecialRewardRepository extends JpaRepository<MQuestSpecialReward, Long>, JpaSpecificationExecutor<MQuestSpecialReward> {

}

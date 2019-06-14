package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestClearRewardWeight;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestClearRewardWeight entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestClearRewardWeightRepository extends JpaRepository<MQuestClearRewardWeight, Long>, JpaSpecificationExecutor<MQuestClearRewardWeight> {

}

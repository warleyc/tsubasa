package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestStageCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestStageCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestStageConditionRepository extends JpaRepository<MQuestStageCondition, Long>, JpaSpecificationExecutor<MQuestStageCondition> {

}

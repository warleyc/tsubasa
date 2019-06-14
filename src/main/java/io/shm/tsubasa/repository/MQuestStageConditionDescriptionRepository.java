package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestStageConditionDescription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestStageConditionDescription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestStageConditionDescriptionRepository extends JpaRepository<MQuestStageConditionDescription, Long>, JpaSpecificationExecutor<MQuestStageConditionDescription> {

}

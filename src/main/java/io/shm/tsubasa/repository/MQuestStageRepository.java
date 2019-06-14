package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestStageRepository extends JpaRepository<MQuestStage, Long>, JpaSpecificationExecutor<MQuestStage> {

}

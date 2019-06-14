package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLuckWeeklyQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLuckWeeklyQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLuckWeeklyQuestStageRepository extends JpaRepository<MLuckWeeklyQuestStage, Long>, JpaSpecificationExecutor<MLuckWeeklyQuestStage> {

}

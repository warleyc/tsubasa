package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MWeeklyQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MWeeklyQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MWeeklyQuestStageRepository extends JpaRepository<MWeeklyQuestStage, Long>, JpaSpecificationExecutor<MWeeklyQuestStage> {

}

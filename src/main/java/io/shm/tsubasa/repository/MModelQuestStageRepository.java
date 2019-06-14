package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MModelQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MModelQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MModelQuestStageRepository extends JpaRepository<MModelQuestStage, Long>, JpaSpecificationExecutor<MModelQuestStage> {

}

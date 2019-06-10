package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAdventQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAdventQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAdventQuestStageRepository extends JpaRepository<MAdventQuestStage, Long>, JpaSpecificationExecutor<MAdventQuestStage> {

}

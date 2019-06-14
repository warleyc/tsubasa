package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTimeattackQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTimeattackQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTimeattackQuestStageRepository extends JpaRepository<MTimeattackQuestStage, Long>, JpaSpecificationExecutor<MTimeattackQuestStage> {

}

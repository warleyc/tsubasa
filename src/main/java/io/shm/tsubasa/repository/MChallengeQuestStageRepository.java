package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MChallengeQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MChallengeQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MChallengeQuestStageRepository extends JpaRepository<MChallengeQuestStage, Long>, JpaSpecificationExecutor<MChallengeQuestStage> {

}

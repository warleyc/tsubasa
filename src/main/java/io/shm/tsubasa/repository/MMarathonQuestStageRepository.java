package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonQuestStageRepository extends JpaRepository<MMarathonQuestStage, Long>, JpaSpecificationExecutor<MMarathonQuestStage> {

}

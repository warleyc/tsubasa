package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuerillaQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuerillaQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuerillaQuestStageRepository extends JpaRepository<MGuerillaQuestStage, Long>, JpaSpecificationExecutor<MGuerillaQuestStage> {

}

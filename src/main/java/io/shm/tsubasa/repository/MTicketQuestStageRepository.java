package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTicketQuestStage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTicketQuestStage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTicketQuestStageRepository extends JpaRepository<MTicketQuestStage, Long>, JpaSpecificationExecutor<MTicketQuestStage> {

}

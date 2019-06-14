package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTicketQuestStageReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTicketQuestStageReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTicketQuestStageRewardRepository extends JpaRepository<MTicketQuestStageReward, Long>, JpaSpecificationExecutor<MTicketQuestStageReward> {

}

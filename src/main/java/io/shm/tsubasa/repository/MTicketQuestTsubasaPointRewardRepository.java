package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTicketQuestTsubasaPointReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTicketQuestTsubasaPointReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTicketQuestTsubasaPointRewardRepository extends JpaRepository<MTicketQuestTsubasaPointReward, Long>, JpaSpecificationExecutor<MTicketQuestTsubasaPointReward> {

}

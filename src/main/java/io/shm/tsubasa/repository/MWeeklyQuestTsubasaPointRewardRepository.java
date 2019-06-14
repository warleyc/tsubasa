package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MWeeklyQuestTsubasaPointReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MWeeklyQuestTsubasaPointReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MWeeklyQuestTsubasaPointRewardRepository extends JpaRepository<MWeeklyQuestTsubasaPointReward, Long>, JpaSpecificationExecutor<MWeeklyQuestTsubasaPointReward> {

}

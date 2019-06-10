package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAdventQuestTsubasaPointReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAdventQuestTsubasaPointReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAdventQuestTsubasaPointRewardRepository extends JpaRepository<MAdventQuestTsubasaPointReward, Long>, JpaSpecificationExecutor<MAdventQuestTsubasaPointReward> {

}

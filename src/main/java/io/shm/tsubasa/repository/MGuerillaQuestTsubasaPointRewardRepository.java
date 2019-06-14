package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuerillaQuestTsubasaPointReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuerillaQuestTsubasaPointReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuerillaQuestTsubasaPointRewardRepository extends JpaRepository<MGuerillaQuestTsubasaPointReward, Long>, JpaSpecificationExecutor<MGuerillaQuestTsubasaPointReward> {

}

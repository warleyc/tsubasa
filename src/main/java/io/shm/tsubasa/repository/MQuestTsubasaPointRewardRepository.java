package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestTsubasaPointReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestTsubasaPointReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestTsubasaPointRewardRepository extends JpaRepository<MQuestTsubasaPointReward, Long>, JpaSpecificationExecutor<MQuestTsubasaPointReward> {

}

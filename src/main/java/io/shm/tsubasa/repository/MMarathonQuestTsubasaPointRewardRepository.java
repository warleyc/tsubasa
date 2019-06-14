package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonQuestTsubasaPointReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonQuestTsubasaPointRewardRepository extends JpaRepository<MMarathonQuestTsubasaPointReward, Long>, JpaSpecificationExecutor<MMarathonQuestTsubasaPointReward> {

}

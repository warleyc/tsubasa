package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonRankingReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonRankingReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonRankingRewardRepository extends JpaRepository<MMarathonRankingReward, Long>, JpaSpecificationExecutor<MMarathonRankingReward> {

}

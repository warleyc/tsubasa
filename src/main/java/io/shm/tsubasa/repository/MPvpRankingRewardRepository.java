package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpRankingReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpRankingReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpRankingRewardRepository extends JpaRepository<MPvpRankingReward, Long>, JpaSpecificationExecutor<MPvpRankingReward> {

}

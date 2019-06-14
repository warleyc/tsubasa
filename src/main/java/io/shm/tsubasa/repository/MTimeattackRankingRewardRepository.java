package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTimeattackRankingReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTimeattackRankingReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTimeattackRankingRewardRepository extends JpaRepository<MTimeattackRankingReward, Long>, JpaSpecificationExecutor<MTimeattackRankingReward> {

}

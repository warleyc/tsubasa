package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLeagueRankingReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLeagueRankingReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLeagueRankingRewardRepository extends JpaRepository<MLeagueRankingReward, Long>, JpaSpecificationExecutor<MLeagueRankingReward> {

}

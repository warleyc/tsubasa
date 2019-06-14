package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MRegulatedLeagueRankingReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MRegulatedLeagueRankingReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MRegulatedLeagueRankingRewardRepository extends JpaRepository<MRegulatedLeagueRankingReward, Long>, JpaSpecificationExecutor<MRegulatedLeagueRankingReward> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLeagueRankingRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLeagueRankingRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLeagueRankingRewardGroupRepository extends JpaRepository<MLeagueRankingRewardGroup, Long>, JpaSpecificationExecutor<MLeagueRankingRewardGroup> {

}

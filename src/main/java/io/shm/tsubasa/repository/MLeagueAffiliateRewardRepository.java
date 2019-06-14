package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLeagueAffiliateReward;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLeagueAffiliateReward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLeagueAffiliateRewardRepository extends JpaRepository<MLeagueAffiliateReward, Long>, JpaSpecificationExecutor<MLeagueAffiliateReward> {

}

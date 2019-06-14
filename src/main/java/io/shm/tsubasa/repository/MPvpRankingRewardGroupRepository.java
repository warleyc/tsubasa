package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpRankingRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpRankingRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpRankingRewardGroupRepository extends JpaRepository<MPvpRankingRewardGroup, Long>, JpaSpecificationExecutor<MPvpRankingRewardGroup> {

}

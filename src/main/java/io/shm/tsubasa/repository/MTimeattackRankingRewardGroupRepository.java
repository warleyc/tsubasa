package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTimeattackRankingRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTimeattackRankingRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTimeattackRankingRewardGroupRepository extends JpaRepository<MTimeattackRankingRewardGroup, Long>, JpaSpecificationExecutor<MTimeattackRankingRewardGroup> {

}

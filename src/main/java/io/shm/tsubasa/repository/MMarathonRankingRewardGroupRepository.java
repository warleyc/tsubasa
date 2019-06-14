package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonRankingRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonRankingRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonRankingRewardGroupRepository extends JpaRepository<MMarathonRankingRewardGroup, Long>, JpaSpecificationExecutor<MMarathonRankingRewardGroup> {

}

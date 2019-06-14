package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPenaltyKickCut;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPenaltyKickCut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPenaltyKickCutRepository extends JpaRepository<MPenaltyKickCut, Long>, JpaSpecificationExecutor<MPenaltyKickCut> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGoalJudgement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGoalJudgement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGoalJudgementRepository extends JpaRepository<MGoalJudgement, Long>, JpaSpecificationExecutor<MGoalJudgement> {

}

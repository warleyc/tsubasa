package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDetachActionSkillCost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDetachActionSkillCost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDetachActionSkillCostRepository extends JpaRepository<MDetachActionSkillCost, Long>, JpaSpecificationExecutor<MDetachActionSkillCost> {

}

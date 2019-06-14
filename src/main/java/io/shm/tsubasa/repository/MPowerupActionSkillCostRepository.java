package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPowerupActionSkillCost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPowerupActionSkillCost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPowerupActionSkillCostRepository extends JpaRepository<MPowerupActionSkillCost, Long>, JpaSpecificationExecutor<MPowerupActionSkillCost> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MInheritActionSkillCost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MInheritActionSkillCost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MInheritActionSkillCostRepository extends JpaRepository<MInheritActionSkillCost, Long>, JpaSpecificationExecutor<MInheritActionSkillCost> {

}

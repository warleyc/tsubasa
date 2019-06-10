package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCardPowerupActionSkill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCardPowerupActionSkill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCardPowerupActionSkillRepository extends JpaRepository<MCardPowerupActionSkill, Long>, JpaSpecificationExecutor<MCardPowerupActionSkill> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MActionSkillCutin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MActionSkillCutin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MActionSkillCutinRepository extends JpaRepository<MActionSkillCutin, Long>, JpaSpecificationExecutor<MActionSkillCutin> {

}

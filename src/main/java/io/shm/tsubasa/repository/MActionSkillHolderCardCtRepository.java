package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MActionSkillHolderCardCt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MActionSkillHolderCardCt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MActionSkillHolderCardCtRepository extends JpaRepository<MActionSkillHolderCardCt, Long>, JpaSpecificationExecutor<MActionSkillHolderCardCt> {

}

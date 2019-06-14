package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MActionSkillHolderCardContent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MActionSkillHolderCardContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MActionSkillHolderCardContentRepository extends JpaRepository<MActionSkillHolderCardContent, Long>, JpaSpecificationExecutor<MActionSkillHolderCardContent> {

}

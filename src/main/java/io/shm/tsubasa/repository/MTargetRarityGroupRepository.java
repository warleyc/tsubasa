package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetRarityGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetRarityGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetRarityGroupRepository extends JpaRepository<MTargetRarityGroup, Long>, JpaSpecificationExecutor<MTargetRarityGroup> {

}

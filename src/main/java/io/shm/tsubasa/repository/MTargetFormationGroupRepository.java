package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetFormationGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetFormationGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetFormationGroupRepository extends JpaRepository<MTargetFormationGroup, Long>, JpaSpecificationExecutor<MTargetFormationGroup> {

}

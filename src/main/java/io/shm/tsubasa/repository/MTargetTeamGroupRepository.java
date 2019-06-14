package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetTeamGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetTeamGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetTeamGroupRepository extends JpaRepository<MTargetTeamGroup, Long>, JpaSpecificationExecutor<MTargetTeamGroup> {

}

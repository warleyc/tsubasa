package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetActionTypeGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetActionTypeGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetActionTypeGroupRepository extends JpaRepository<MTargetActionTypeGroup, Long>, JpaSpecificationExecutor<MTargetActionTypeGroup> {

}

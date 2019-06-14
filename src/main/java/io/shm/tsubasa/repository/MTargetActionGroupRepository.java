package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetActionGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetActionGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetActionGroupRepository extends JpaRepository<MTargetActionGroup, Long>, JpaSpecificationExecutor<MTargetActionGroup> {

}

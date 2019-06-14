package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetNationalityGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetNationalityGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetNationalityGroupRepository extends JpaRepository<MTargetNationalityGroup, Long>, JpaSpecificationExecutor<MTargetNationalityGroup> {

}

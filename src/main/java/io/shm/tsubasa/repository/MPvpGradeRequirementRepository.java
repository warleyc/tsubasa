package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpGradeRequirement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpGradeRequirement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpGradeRequirementRepository extends JpaRepository<MPvpGradeRequirement, Long>, JpaSpecificationExecutor<MPvpGradeRequirement> {

}

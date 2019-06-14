package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpGrade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpGrade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpGradeRepository extends JpaRepository<MPvpGrade, Long>, JpaSpecificationExecutor<MPvpGrade> {

}

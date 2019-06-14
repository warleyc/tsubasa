package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionTrajectory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionTrajectory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionTrajectoryRepository extends JpaRepository<MGachaRenditionTrajectory, Long>, JpaSpecificationExecutor<MGachaRenditionTrajectory> {

}

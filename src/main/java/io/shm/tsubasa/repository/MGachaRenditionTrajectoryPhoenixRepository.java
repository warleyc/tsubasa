package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionTrajectoryPhoenix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionTrajectoryPhoenixRepository extends JpaRepository<MGachaRenditionTrajectoryPhoenix, Long>, JpaSpecificationExecutor<MGachaRenditionTrajectoryPhoenix> {

}

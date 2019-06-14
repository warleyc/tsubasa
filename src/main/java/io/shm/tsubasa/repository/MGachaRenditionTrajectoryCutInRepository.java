package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionTrajectoryCutIn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionTrajectoryCutInRepository extends JpaRepository<MGachaRenditionTrajectoryCutIn, Long>, JpaSpecificationExecutor<MGachaRenditionTrajectoryCutIn> {

}

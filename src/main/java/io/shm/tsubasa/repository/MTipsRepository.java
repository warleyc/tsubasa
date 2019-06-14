package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTips;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTips entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTipsRepository extends JpaRepository<MTips, Long>, JpaSpecificationExecutor<MTips> {

}

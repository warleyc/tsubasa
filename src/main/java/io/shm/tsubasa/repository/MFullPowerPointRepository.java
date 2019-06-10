package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MFullPowerPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MFullPowerPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MFullPowerPointRepository extends JpaRepository<MFullPowerPoint, Long>, JpaSpecificationExecutor<MFullPowerPoint> {

}

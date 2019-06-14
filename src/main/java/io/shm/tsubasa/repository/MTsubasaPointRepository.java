package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTsubasaPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTsubasaPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTsubasaPointRepository extends JpaRepository<MTsubasaPoint, Long>, JpaSpecificationExecutor<MTsubasaPoint> {

}

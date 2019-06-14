package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MStamp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MStamp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MStampRepository extends JpaRepository<MStamp, Long>, JpaSpecificationExecutor<MStamp> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpPlayerStamp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpPlayerStamp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpPlayerStampRepository extends JpaRepository<MPvpPlayerStamp, Long>, JpaSpecificationExecutor<MPvpPlayerStamp> {

}

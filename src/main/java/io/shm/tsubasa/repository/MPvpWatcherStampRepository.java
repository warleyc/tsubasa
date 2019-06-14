package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpWatcherStamp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpWatcherStamp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpWatcherStampRepository extends JpaRepository<MPvpWatcherStamp, Long>, JpaSpecificationExecutor<MPvpWatcherStamp> {

}

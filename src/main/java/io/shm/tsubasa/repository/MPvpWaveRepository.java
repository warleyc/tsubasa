package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPvpWave;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPvpWave entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPvpWaveRepository extends JpaRepository<MPvpWave, Long>, JpaSpecificationExecutor<MPvpWave> {

}

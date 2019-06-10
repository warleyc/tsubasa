package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MActionLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MActionLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MActionLevelRepository extends JpaRepository<MActionLevel, Long>, JpaSpecificationExecutor<MActionLevel> {

}

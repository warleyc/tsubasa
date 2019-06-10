package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCardLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCardLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCardLevelRepository extends JpaRepository<MCardLevel, Long>, JpaSpecificationExecutor<MCardLevel> {

}

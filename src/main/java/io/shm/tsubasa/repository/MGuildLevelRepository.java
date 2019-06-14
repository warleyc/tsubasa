package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildLevelRepository extends JpaRepository<MGuildLevel, Long>, JpaSpecificationExecutor<MGuildLevel> {

}

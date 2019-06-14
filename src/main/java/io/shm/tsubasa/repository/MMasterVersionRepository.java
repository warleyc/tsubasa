package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMasterVersion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMasterVersion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMasterVersionRepository extends JpaRepository<MMasterVersion, Long>, JpaSpecificationExecutor<MMasterVersion> {

}

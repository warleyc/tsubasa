package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMissionRepository extends JpaRepository<MMission, Long>, JpaSpecificationExecutor<MMission> {

}

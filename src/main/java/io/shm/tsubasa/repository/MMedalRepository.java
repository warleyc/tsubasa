package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMedal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMedal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMedalRepository extends JpaRepository<MMedal, Long>, JpaSpecificationExecutor<MMedal> {

}

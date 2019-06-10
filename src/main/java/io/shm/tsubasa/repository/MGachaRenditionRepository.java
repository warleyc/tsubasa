package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRendition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRendition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionRepository extends JpaRepository<MGachaRendition, Long>, JpaSpecificationExecutor<MGachaRendition> {

}

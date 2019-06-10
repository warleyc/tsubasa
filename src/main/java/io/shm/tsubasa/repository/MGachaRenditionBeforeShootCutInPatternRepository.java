package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInPattern;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionBeforeShootCutInPattern entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionBeforeShootCutInPatternRepository extends JpaRepository<MGachaRenditionBeforeShootCutInPattern, Long>, JpaSpecificationExecutor<MGachaRenditionBeforeShootCutInPattern> {

}

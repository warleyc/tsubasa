package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionExtraCutin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionExtraCutin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionExtraCutinRepository extends JpaRepository<MGachaRenditionExtraCutin, Long>, JpaSpecificationExecutor<MGachaRenditionExtraCutin> {

}

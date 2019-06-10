package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutIn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionAfterInputCutIn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionAfterInputCutInRepository extends JpaRepository<MGachaRenditionAfterInputCutIn, Long>, JpaSpecificationExecutor<MGachaRenditionAfterInputCutIn> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutInTextColor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionAfterInputCutInTextColor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionAfterInputCutInTextColorRepository extends JpaRepository<MGachaRenditionAfterInputCutInTextColor, Long>, JpaSpecificationExecutor<MGachaRenditionAfterInputCutInTextColor> {

}

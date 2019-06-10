package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionKicker;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionKicker entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionKickerRepository extends JpaRepository<MGachaRenditionKicker, Long>, JpaSpecificationExecutor<MGachaRenditionKicker> {

}

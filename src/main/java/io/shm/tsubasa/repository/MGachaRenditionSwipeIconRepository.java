package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionSwipeIcon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionSwipeIcon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionSwipeIconRepository extends JpaRepository<MGachaRenditionSwipeIcon, Long>, JpaSpecificationExecutor<MGachaRenditionSwipeIcon> {

}

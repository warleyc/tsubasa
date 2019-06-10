package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionFirstGimmick;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionFirstGimmick entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionFirstGimmickRepository extends JpaRepository<MGachaRenditionFirstGimmick, Long>, JpaSpecificationExecutor<MGachaRenditionFirstGimmick> {

}

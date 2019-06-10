package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionBall;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionBall entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionBallRepository extends JpaRepository<MGachaRenditionBall, Long>, JpaSpecificationExecutor<MGachaRenditionBall> {

}

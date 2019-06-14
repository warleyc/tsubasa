package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTutorialMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTutorialMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTutorialMessageRepository extends JpaRepository<MTutorialMessage, Long>, JpaSpecificationExecutor<MTutorialMessage> {

}

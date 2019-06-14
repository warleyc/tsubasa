package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MSceneTutorialMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MSceneTutorialMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MSceneTutorialMessageRepository extends JpaRepository<MSceneTutorialMessage, Long>, JpaSpecificationExecutor<MSceneTutorialMessage> {

}

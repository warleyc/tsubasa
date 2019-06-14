package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MRegulationMatchTutorialMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MRegulationMatchTutorialMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MRegulationMatchTutorialMessageRepository extends JpaRepository<MRegulationMatchTutorialMessage, Long>, JpaSpecificationExecutor<MRegulationMatchTutorialMessage> {

}

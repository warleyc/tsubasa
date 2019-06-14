package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestCoop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestCoop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestCoopRepository extends JpaRepository<MQuestCoop, Long>, JpaSpecificationExecutor<MQuestCoop> {

}

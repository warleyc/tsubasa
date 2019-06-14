package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestWorldRepository extends JpaRepository<MQuestWorld, Long>, JpaSpecificationExecutor<MQuestWorld> {

}

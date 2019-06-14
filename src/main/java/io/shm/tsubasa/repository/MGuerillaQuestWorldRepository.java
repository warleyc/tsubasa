package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuerillaQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuerillaQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuerillaQuestWorldRepository extends JpaRepository<MGuerillaQuestWorld, Long>, JpaSpecificationExecutor<MGuerillaQuestWorld> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAdventQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAdventQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAdventQuestWorldRepository extends JpaRepository<MAdventQuestWorld, Long>, JpaSpecificationExecutor<MAdventQuestWorld> {

}

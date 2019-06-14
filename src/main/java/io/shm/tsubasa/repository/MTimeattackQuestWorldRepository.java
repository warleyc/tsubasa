package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTimeattackQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTimeattackQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTimeattackQuestWorldRepository extends JpaRepository<MTimeattackQuestWorld, Long>, JpaSpecificationExecutor<MTimeattackQuestWorld> {

}

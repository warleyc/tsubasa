package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MChallengeQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MChallengeQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MChallengeQuestWorldRepository extends JpaRepository<MChallengeQuestWorld, Long>, JpaSpecificationExecutor<MChallengeQuestWorld> {

}

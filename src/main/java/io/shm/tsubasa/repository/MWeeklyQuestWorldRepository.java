package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MWeeklyQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MWeeklyQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MWeeklyQuestWorldRepository extends JpaRepository<MWeeklyQuestWorld, Long>, JpaSpecificationExecutor<MWeeklyQuestWorld> {

}

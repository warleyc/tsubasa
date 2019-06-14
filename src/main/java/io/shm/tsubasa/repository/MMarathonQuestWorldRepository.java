package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonQuestWorldRepository extends JpaRepository<MMarathonQuestWorld, Long>, JpaSpecificationExecutor<MMarathonQuestWorld> {

}

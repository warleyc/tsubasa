package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLuckWeeklyQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLuckWeeklyQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLuckWeeklyQuestWorldRepository extends JpaRepository<MLuckWeeklyQuestWorld, Long>, JpaSpecificationExecutor<MLuckWeeklyQuestWorld> {

}

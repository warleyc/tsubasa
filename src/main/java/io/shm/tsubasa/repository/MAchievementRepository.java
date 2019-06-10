package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAchievement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAchievement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAchievementRepository extends JpaRepository<MAchievement, Long>, JpaSpecificationExecutor<MAchievement> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTeamEffectLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTeamEffectLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTeamEffectLevelRepository extends JpaRepository<MTeamEffectLevel, Long>, JpaSpecificationExecutor<MTeamEffectLevel> {

}

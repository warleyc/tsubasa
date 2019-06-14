package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLeagueEffect;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLeagueEffect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLeagueEffectRepository extends JpaRepository<MLeagueEffect, Long>, JpaSpecificationExecutor<MLeagueEffect> {

}

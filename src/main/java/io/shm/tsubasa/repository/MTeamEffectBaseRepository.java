package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTeamEffectBase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTeamEffectBase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTeamEffectBaseRepository extends JpaRepository<MTeamEffectBase, Long>, JpaSpecificationExecutor<MTeamEffectBase> {

}

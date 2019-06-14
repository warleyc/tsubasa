package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTeamEffectRarity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTeamEffectRarity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTeamEffectRarityRepository extends JpaRepository<MTeamEffectRarity, Long>, JpaSpecificationExecutor<MTeamEffectRarity> {

}

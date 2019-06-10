package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MEncountersBonus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MEncountersBonus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MEncountersBonusRepository extends JpaRepository<MEncountersBonus, Long>, JpaSpecificationExecutor<MEncountersBonus> {

}

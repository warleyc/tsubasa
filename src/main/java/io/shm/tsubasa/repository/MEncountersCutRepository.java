package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MEncountersCut;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MEncountersCut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MEncountersCutRepository extends JpaRepository<MEncountersCut, Long>, JpaSpecificationExecutor<MEncountersCut> {

}

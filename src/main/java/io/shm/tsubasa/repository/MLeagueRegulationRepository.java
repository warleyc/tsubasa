package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLeagueRegulation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLeagueRegulation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLeagueRegulationRepository extends JpaRepository<MLeagueRegulation, Long>, JpaSpecificationExecutor<MLeagueRegulation> {

}

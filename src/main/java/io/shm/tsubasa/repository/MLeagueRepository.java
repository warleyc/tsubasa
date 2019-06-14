package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLeague;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLeague entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLeagueRepository extends JpaRepository<MLeague, Long>, JpaSpecificationExecutor<MLeague> {

}

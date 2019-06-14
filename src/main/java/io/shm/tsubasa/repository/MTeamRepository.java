package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTeam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTeam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTeamRepository extends JpaRepository<MTeam, Long>, JpaSpecificationExecutor<MTeam> {

}

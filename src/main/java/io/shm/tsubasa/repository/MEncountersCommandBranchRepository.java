package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MEncountersCommandBranch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MEncountersCommandBranch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MEncountersCommandBranchRepository extends JpaRepository<MEncountersCommandBranch, Long>, JpaSpecificationExecutor<MEncountersCommandBranch> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MUserRank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MUserRank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MUserRankRepository extends JpaRepository<MUserRank, Long>, JpaSpecificationExecutor<MUserRank> {

}

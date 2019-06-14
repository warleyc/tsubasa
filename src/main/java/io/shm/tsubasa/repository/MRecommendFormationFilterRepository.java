package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MRecommendFormationFilter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MRecommendFormationFilter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MRecommendFormationFilterRepository extends JpaRepository<MRecommendFormationFilter, Long>, JpaSpecificationExecutor<MRecommendFormationFilter> {

}

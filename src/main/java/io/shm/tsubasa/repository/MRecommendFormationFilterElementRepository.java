package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MRecommendFormationFilterElement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MRecommendFormationFilterElement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MRecommendFormationFilterElementRepository extends JpaRepository<MRecommendFormationFilterElement, Long>, JpaSpecificationExecutor<MRecommendFormationFilterElement> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDeckRarityConditionDescription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDeckRarityConditionDescription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDeckRarityConditionDescriptionRepository extends JpaRepository<MDeckRarityConditionDescription, Long>, JpaSpecificationExecutor<MDeckRarityConditionDescription> {

}

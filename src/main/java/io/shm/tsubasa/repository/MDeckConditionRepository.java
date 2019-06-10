package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDeckCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDeckCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDeckConditionRepository extends JpaRepository<MDeckCondition, Long>, JpaSpecificationExecutor<MDeckCondition> {

}

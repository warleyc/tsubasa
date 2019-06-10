package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MArousalItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MArousalItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MArousalItemRepository extends JpaRepository<MArousalItem, Long>, JpaSpecificationExecutor<MArousalItem> {

}

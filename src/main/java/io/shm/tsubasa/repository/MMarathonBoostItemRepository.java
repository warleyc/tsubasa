package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonBoostItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonBoostItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonBoostItemRepository extends JpaRepository<MMarathonBoostItem, Long>, JpaSpecificationExecutor<MMarathonBoostItem> {

}

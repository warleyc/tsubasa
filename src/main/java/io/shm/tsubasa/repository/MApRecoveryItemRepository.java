package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MApRecoveryItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MApRecoveryItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MApRecoveryItemRepository extends JpaRepository<MApRecoveryItem, Long>, JpaSpecificationExecutor<MApRecoveryItem> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MKeeperCutAction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MKeeperCutAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MKeeperCutActionRepository extends JpaRepository<MKeeperCutAction, Long>, JpaSpecificationExecutor<MKeeperCutAction> {

}

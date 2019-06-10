package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCutAction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCutAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCutActionRepository extends JpaRepository<MCutAction, Long>, JpaSpecificationExecutor<MCutAction> {

}

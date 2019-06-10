package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MActionRepository extends JpaRepository<MAction, Long>, JpaSpecificationExecutor<MAction> {

}

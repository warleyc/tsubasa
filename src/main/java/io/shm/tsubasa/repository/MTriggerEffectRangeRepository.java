package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTriggerEffectRange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTriggerEffectRange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTriggerEffectRangeRepository extends JpaRepository<MTriggerEffectRange, Long>, JpaSpecificationExecutor<MTriggerEffectRange> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTriggerEffectBase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTriggerEffectBase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTriggerEffectBaseRepository extends JpaRepository<MTriggerEffectBase, Long>, JpaSpecificationExecutor<MTriggerEffectBase> {

}

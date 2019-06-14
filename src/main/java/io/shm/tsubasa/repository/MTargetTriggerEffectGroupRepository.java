package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetTriggerEffectGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetTriggerEffectGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetTriggerEffectGroupRepository extends JpaRepository<MTargetTriggerEffectGroup, Long>, JpaSpecificationExecutor<MTargetTriggerEffectGroup> {

}

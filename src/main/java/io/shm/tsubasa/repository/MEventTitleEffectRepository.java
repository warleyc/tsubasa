package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MEventTitleEffect;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MEventTitleEffect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MEventTitleEffectRepository extends JpaRepository<MEventTitleEffect, Long>, JpaSpecificationExecutor<MEventTitleEffect> {

}

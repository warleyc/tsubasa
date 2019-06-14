package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildEffectLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildEffectLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildEffectLevelRepository extends JpaRepository<MGuildEffectLevel, Long>, JpaSpecificationExecutor<MGuildEffectLevel> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPassiveEffectRange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPassiveEffectRange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPassiveEffectRangeRepository extends JpaRepository<MPassiveEffectRange, Long>, JpaSpecificationExecutor<MPassiveEffectRange> {

}

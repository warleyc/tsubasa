package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCardPlayableAssets;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCardPlayableAssets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCardPlayableAssetsRepository extends JpaRepository<MCardPlayableAssets, Long>, JpaSpecificationExecutor<MCardPlayableAssets> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCardIllustAssets;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCardIllustAssets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCardIllustAssetsRepository extends JpaRepository<MCardIllustAssets, Long>, JpaSpecificationExecutor<MCardIllustAssets> {

}

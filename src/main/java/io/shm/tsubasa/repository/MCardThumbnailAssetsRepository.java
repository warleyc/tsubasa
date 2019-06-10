package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCardThumbnailAssets;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCardThumbnailAssets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCardThumbnailAssetsRepository extends JpaRepository<MCardThumbnailAssets, Long>, JpaSpecificationExecutor<MCardThumbnailAssets> {

}

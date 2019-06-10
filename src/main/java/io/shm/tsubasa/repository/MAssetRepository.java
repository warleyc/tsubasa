package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAsset;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAsset entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAssetRepository extends JpaRepository<MAsset, Long>, JpaSpecificationExecutor<MAsset> {

}

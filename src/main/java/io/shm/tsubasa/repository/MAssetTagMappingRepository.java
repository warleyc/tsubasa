package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAssetTagMapping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAssetTagMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAssetTagMappingRepository extends JpaRepository<MAssetTagMapping, Long>, JpaSpecificationExecutor<MAssetTagMapping> {

}

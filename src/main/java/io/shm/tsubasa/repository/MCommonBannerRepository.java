package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCommonBanner;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCommonBanner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCommonBannerRepository extends JpaRepository<MCommonBanner, Long>, JpaSpecificationExecutor<MCommonBanner> {

}

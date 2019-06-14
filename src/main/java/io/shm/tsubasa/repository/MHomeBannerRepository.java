package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MHomeBanner;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MHomeBanner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MHomeBannerRepository extends JpaRepository<MHomeBanner, Long>, JpaSpecificationExecutor<MHomeBanner> {

}

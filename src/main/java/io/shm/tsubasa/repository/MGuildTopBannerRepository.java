package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildTopBanner;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildTopBanner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildTopBannerRepository extends JpaRepository<MGuildTopBanner, Long>, JpaSpecificationExecutor<MGuildTopBanner> {

}

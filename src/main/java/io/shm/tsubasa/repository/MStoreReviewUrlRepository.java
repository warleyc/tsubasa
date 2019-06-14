package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MStoreReviewUrl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MStoreReviewUrl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MStoreReviewUrlRepository extends JpaRepository<MStoreReviewUrl, Long>, JpaSpecificationExecutor<MStoreReviewUrl> {

}

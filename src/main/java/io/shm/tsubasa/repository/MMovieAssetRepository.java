package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMovieAsset;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMovieAsset entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMovieAssetRepository extends JpaRepository<MMovieAsset, Long>, JpaSpecificationExecutor<MMovieAsset> {

}

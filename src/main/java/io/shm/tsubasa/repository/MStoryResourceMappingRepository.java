package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MStoryResourceMapping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MStoryResourceMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MStoryResourceMappingRepository extends JpaRepository<MStoryResourceMapping, Long>, JpaSpecificationExecutor<MStoryResourceMapping> {

}

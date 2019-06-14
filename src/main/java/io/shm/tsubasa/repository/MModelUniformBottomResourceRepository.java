package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MModelUniformBottomResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MModelUniformBottomResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MModelUniformBottomResourceRepository extends JpaRepository<MModelUniformBottomResource, Long>, JpaSpecificationExecutor<MModelUniformBottomResource> {

}

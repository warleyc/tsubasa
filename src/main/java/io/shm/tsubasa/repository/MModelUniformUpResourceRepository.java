package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MModelUniformUpResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MModelUniformUpResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MModelUniformUpResourceRepository extends JpaRepository<MModelUniformUpResource, Long>, JpaSpecificationExecutor<MModelUniformUpResource> {

}

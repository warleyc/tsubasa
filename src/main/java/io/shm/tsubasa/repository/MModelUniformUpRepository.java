package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MModelUniformUp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MModelUniformUp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MModelUniformUpRepository extends JpaRepository<MModelUniformUp, Long>, JpaSpecificationExecutor<MModelUniformUp> {

}

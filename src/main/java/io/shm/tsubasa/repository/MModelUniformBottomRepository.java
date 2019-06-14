package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MModelUniformBottom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MModelUniformBottom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MModelUniformBottomRepository extends JpaRepository<MModelUniformBottom, Long>, JpaSpecificationExecutor<MModelUniformBottom> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MUniformBottom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MUniformBottom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MUniformBottomRepository extends JpaRepository<MUniformBottom, Long>, JpaSpecificationExecutor<MUniformBottom> {

}

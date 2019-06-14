package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MUniformUp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MUniformUp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MUniformUpRepository extends JpaRepository<MUniformUp, Long>, JpaSpecificationExecutor<MUniformUp> {

}

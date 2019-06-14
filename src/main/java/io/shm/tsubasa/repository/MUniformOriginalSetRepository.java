package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MUniformOriginalSet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MUniformOriginalSet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MUniformOriginalSetRepository extends JpaRepository<MUniformOriginalSet, Long>, JpaSpecificationExecutor<MUniformOriginalSet> {

}

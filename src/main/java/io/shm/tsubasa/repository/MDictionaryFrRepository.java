package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryFr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryFr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryFrRepository extends JpaRepository<MDictionaryFr, Long>, JpaSpecificationExecutor<MDictionaryFr> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryEs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryEs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryEsRepository extends JpaRepository<MDictionaryEs, Long>, JpaSpecificationExecutor<MDictionaryEs> {

}

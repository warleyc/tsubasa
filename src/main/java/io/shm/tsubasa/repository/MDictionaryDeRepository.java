package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryDe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryDe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryDeRepository extends JpaRepository<MDictionaryDe, Long>, JpaSpecificationExecutor<MDictionaryDe> {

}

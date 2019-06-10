package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryEn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryEn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryEnRepository extends JpaRepository<MDictionaryEn, Long>, JpaSpecificationExecutor<MDictionaryEn> {

}

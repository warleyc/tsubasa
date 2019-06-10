package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryZh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryZh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryZhRepository extends JpaRepository<MDictionaryZh, Long>, JpaSpecificationExecutor<MDictionaryZh> {

}

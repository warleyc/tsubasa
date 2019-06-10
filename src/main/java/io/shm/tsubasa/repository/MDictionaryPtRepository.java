package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryPt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryPt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryPtRepository extends JpaRepository<MDictionaryPt, Long>, JpaSpecificationExecutor<MDictionaryPt> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryIt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryIt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryItRepository extends JpaRepository<MDictionaryIt, Long>, JpaSpecificationExecutor<MDictionaryIt> {

}

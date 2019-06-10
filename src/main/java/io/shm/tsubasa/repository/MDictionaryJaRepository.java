package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryJa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryJa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryJaRepository extends JpaRepository<MDictionaryJa, Long>, JpaSpecificationExecutor<MDictionaryJa> {

}

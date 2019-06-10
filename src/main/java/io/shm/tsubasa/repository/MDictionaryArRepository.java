package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDictionaryAr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDictionaryAr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDictionaryArRepository extends JpaRepository<MDictionaryAr, Long>, JpaSpecificationExecutor<MDictionaryAr> {

}

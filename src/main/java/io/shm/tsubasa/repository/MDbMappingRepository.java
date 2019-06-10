package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDbMapping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDbMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDbMappingRepository extends JpaRepository<MDbMapping, Long>, JpaSpecificationExecutor<MDbMapping> {

}

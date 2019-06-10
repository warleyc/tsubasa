package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCut;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCutRepository extends JpaRepository<MCut, Long>, JpaSpecificationExecutor<MCut> {

}

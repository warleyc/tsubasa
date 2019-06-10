package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MArousal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MArousal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MArousalRepository extends JpaRepository<MArousal, Long>, JpaSpecificationExecutor<MArousal> {

}

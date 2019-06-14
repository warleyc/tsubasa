package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMatchEnvironment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMatchEnvironment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMatchEnvironmentRepository extends JpaRepository<MMatchEnvironment, Long>, JpaSpecificationExecutor<MMatchEnvironment> {

}

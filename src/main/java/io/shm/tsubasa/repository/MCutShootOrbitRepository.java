package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCutShootOrbit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCutShootOrbit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCutShootOrbitRepository extends JpaRepository<MCutShootOrbit, Long>, JpaSpecificationExecutor<MCutShootOrbit> {

}

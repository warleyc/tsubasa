package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MShoot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MShoot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MShootRepository extends JpaRepository<MShoot, Long>, JpaSpecificationExecutor<MShoot> {

}

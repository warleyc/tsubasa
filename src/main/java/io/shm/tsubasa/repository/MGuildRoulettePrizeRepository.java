package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildRoulettePrize;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildRoulettePrize entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildRoulettePrizeRepository extends JpaRepository<MGuildRoulettePrize, Long>, JpaSpecificationExecutor<MGuildRoulettePrize> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MSellCardCoin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MSellCardCoin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MSellCardCoinRepository extends JpaRepository<MSellCardCoin, Long>, JpaSpecificationExecutor<MSellCardCoin> {

}

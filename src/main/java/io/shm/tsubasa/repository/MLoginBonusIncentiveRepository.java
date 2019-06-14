package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLoginBonusIncentive;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLoginBonusIncentive entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLoginBonusIncentiveRepository extends JpaRepository<MLoginBonusIncentive, Long>, JpaSpecificationExecutor<MLoginBonusIncentive> {

}

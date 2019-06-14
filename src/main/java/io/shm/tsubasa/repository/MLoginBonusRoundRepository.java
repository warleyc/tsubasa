package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLoginBonusRound;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLoginBonusRound entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLoginBonusRoundRepository extends JpaRepository<MLoginBonusRound, Long>, JpaSpecificationExecutor<MLoginBonusRound> {

}

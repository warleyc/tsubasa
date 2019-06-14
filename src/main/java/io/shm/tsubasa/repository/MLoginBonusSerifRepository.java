package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLoginBonusSerif;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLoginBonusSerif entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLoginBonusSerifRepository extends JpaRepository<MLoginBonusSerif, Long>, JpaSpecificationExecutor<MLoginBonusSerif> {

}

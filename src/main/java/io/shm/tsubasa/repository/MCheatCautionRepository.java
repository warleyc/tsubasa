package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCheatCaution;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCheatCaution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCheatCautionRepository extends JpaRepository<MCheatCaution, Long>, JpaSpecificationExecutor<MCheatCaution> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCharacterScoreCut;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCharacterScoreCut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCharacterScoreCutRepository extends JpaRepository<MCharacterScoreCut, Long>, JpaSpecificationExecutor<MCharacterScoreCut> {

}

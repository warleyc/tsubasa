package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MPlayableCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MPlayableCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MPlayableCardRepository extends JpaRepository<MPlayableCard, Long>, JpaSpecificationExecutor<MPlayableCard> {

}

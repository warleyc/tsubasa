package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MContentableCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MContentableCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MContentableCardRepository extends JpaRepository<MContentableCard, Long>, JpaSpecificationExecutor<MContentableCard> {

}

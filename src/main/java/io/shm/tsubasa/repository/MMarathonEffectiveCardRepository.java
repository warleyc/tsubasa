package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonEffectiveCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonEffectiveCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonEffectiveCardRepository extends JpaRepository<MMarathonEffectiveCard, Long>, JpaSpecificationExecutor<MMarathonEffectiveCard> {

}

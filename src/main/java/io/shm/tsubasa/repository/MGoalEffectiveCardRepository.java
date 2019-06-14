package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGoalEffectiveCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGoalEffectiveCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGoalEffectiveCardRepository extends JpaRepository<MGoalEffectiveCard, Long>, JpaSpecificationExecutor<MGoalEffectiveCard> {

}

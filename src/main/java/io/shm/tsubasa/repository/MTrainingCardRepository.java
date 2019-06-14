package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTrainingCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTrainingCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTrainingCardRepository extends JpaRepository<MTrainingCard, Long>, JpaSpecificationExecutor<MTrainingCard> {

}

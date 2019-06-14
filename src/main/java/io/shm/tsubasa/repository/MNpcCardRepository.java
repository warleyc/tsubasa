package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MNpcCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MNpcCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MNpcCardRepository extends JpaRepository<MNpcCard, Long>, JpaSpecificationExecutor<MNpcCard> {

}

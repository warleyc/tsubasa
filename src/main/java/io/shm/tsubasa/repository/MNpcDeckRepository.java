package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MNpcDeck;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MNpcDeck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MNpcDeckRepository extends JpaRepository<MNpcDeck, Long>, JpaSpecificationExecutor<MNpcDeck> {

}

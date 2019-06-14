package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MInitUserDeck;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MInitUserDeck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MInitUserDeckRepository extends JpaRepository<MInitUserDeck, Long>, JpaSpecificationExecutor<MInitUserDeck> {

}

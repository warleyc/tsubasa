package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDummyOpponent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDummyOpponent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDummyOpponentRepository extends JpaRepository<MDummyOpponent, Long>, JpaSpecificationExecutor<MDummyOpponent> {

}

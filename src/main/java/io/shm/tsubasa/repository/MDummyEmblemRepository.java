package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDummyEmblem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDummyEmblem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDummyEmblemRepository extends JpaRepository<MDummyEmblem, Long>, JpaSpecificationExecutor<MDummyEmblem> {

}

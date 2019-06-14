package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MSituationAnnounce;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MSituationAnnounce entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MSituationAnnounceRepository extends JpaRepository<MSituationAnnounce, Long>, JpaSpecificationExecutor<MSituationAnnounce> {

}

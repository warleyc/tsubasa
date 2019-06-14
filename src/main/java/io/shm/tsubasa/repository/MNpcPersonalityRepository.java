package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MNpcPersonality;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MNpcPersonality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MNpcPersonalityRepository extends JpaRepository<MNpcPersonality, Long>, JpaSpecificationExecutor<MNpcPersonality> {

}

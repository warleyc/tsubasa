package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MSellCardMedal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MSellCardMedal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MSellCardMedalRepository extends JpaRepository<MSellCardMedal, Long>, JpaSpecificationExecutor<MSellCardMedal> {

}

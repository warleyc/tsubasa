package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTicketQuestWorld;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTicketQuestWorld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTicketQuestWorldRepository extends JpaRepository<MTicketQuestWorld, Long>, JpaSpecificationExecutor<MTicketQuestWorld> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestTicketRepository extends JpaRepository<MQuestTicket, Long>, JpaSpecificationExecutor<MQuestTicket> {

}

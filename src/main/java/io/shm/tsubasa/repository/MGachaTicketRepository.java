package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaTicketRepository extends JpaRepository<MGachaTicket, Long>, JpaSpecificationExecutor<MGachaTicket> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MChatSystemMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MChatSystemMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MChatSystemMessageRepository extends JpaRepository<MChatSystemMessage, Long>, JpaSpecificationExecutor<MChatSystemMessage> {

}

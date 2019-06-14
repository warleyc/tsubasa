package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MSoundBankEvent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MSoundBankEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MSoundBankEventRepository extends JpaRepository<MSoundBankEvent, Long>, JpaSpecificationExecutor<MSoundBankEvent> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MSoundBank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MSoundBank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MSoundBankRepository extends JpaRepository<MSoundBank, Long>, JpaSpecificationExecutor<MSoundBank> {

}

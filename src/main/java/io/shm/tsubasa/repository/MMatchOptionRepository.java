package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMatchOption;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMatchOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMatchOptionRepository extends JpaRepository<MMatchOption, Long>, JpaSpecificationExecutor<MMatchOption> {

}

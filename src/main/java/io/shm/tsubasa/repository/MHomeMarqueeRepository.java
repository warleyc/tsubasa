package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MHomeMarquee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MHomeMarquee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MHomeMarqueeRepository extends JpaRepository<MHomeMarquee, Long>, JpaSpecificationExecutor<MHomeMarquee> {

}

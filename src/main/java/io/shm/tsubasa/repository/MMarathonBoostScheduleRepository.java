package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MMarathonBoostSchedule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMarathonBoostSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMarathonBoostScheduleRepository extends JpaRepository<MMarathonBoostSchedule, Long>, JpaSpecificationExecutor<MMarathonBoostSchedule> {

}

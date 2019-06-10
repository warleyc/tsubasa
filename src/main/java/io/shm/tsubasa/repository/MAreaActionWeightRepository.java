package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAreaActionWeight;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAreaActionWeight entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAreaActionWeightRepository extends JpaRepository<MAreaActionWeight, Long>, JpaSpecificationExecutor<MAreaActionWeight> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MUserPolicyUpdateDate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MUserPolicyUpdateDate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MUserPolicyUpdateDateRepository extends JpaRepository<MUserPolicyUpdateDate, Long>, JpaSpecificationExecutor<MUserPolicyUpdateDate> {

}

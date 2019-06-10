package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MEncountersCommandCompatibility;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MEncountersCommandCompatibility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MEncountersCommandCompatibilityRepository extends JpaRepository<MEncountersCommandCompatibility, Long>, JpaSpecificationExecutor<MEncountersCommandCompatibility> {

}

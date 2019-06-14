package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildMission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildMission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildMissionRepository extends JpaRepository<MGuildMission, Long>, JpaSpecificationExecutor<MGuildMission> {

}

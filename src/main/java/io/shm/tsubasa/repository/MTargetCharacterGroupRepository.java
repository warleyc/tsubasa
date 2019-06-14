package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetCharacterGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetCharacterGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetCharacterGroupRepository extends JpaRepository<MTargetCharacterGroup, Long>, JpaSpecificationExecutor<MTargetCharacterGroup> {

}

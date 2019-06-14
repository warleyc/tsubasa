package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MTargetPlayableCardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MTargetPlayableCardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MTargetPlayableCardGroupRepository extends JpaRepository<MTargetPlayableCardGroup, Long>, JpaSpecificationExecutor<MTargetPlayableCardGroup> {

}

package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestRewardGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestRewardGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestRewardGroupRepository extends JpaRepository<MQuestRewardGroup, Long>, JpaSpecificationExecutor<MQuestRewardGroup> {

}

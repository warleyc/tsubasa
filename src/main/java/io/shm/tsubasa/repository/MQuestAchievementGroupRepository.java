package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestAchievementGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestAchievementGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestAchievementGroupRepository extends JpaRepository<MQuestAchievementGroup, Long>, JpaSpecificationExecutor<MQuestAchievementGroup> {

}

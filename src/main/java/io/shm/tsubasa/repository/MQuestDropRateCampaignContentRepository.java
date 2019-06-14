package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MQuestDropRateCampaignContent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MQuestDropRateCampaignContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MQuestDropRateCampaignContentRepository extends JpaRepository<MQuestDropRateCampaignContent, Long>, JpaSpecificationExecutor<MQuestDropRateCampaignContent> {

}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestDropRateCampaignContent} and its DTO {@link MQuestDropRateCampaignContentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestDropRateCampaignContentMapper extends EntityMapper<MQuestDropRateCampaignContentDTO, MQuestDropRateCampaignContent> {



    default MQuestDropRateCampaignContent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestDropRateCampaignContent mQuestDropRateCampaignContent = new MQuestDropRateCampaignContent();
        mQuestDropRateCampaignContent.setId(id);
        return mQuestDropRateCampaignContent;
    }
}

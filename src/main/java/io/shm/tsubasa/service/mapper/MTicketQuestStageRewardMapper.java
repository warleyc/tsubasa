package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTicketQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTicketQuestStageReward} and its DTO {@link MTicketQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTicketQuestStageRewardMapper extends EntityMapper<MTicketQuestStageRewardDTO, MTicketQuestStageReward> {



    default MTicketQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTicketQuestStageReward mTicketQuestStageReward = new MTicketQuestStageReward();
        mTicketQuestStageReward.setId(id);
        return mTicketQuestStageReward;
    }
}

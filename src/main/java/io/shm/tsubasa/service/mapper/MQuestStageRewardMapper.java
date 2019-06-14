package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestStageReward} and its DTO {@link MQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestStageRewardMapper extends EntityMapper<MQuestStageRewardDTO, MQuestStageReward> {



    default MQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestStageReward mQuestStageReward = new MQuestStageReward();
        mQuestStageReward.setId(id);
        return mQuestStageReward;
    }
}

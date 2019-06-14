package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MWeeklyQuestStageReward} and its DTO {@link MWeeklyQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MWeeklyQuestStageRewardMapper extends EntityMapper<MWeeklyQuestStageRewardDTO, MWeeklyQuestStageReward> {



    default MWeeklyQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MWeeklyQuestStageReward mWeeklyQuestStageReward = new MWeeklyQuestStageReward();
        mWeeklyQuestStageReward.setId(id);
        return mWeeklyQuestStageReward;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestGoalRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestGoalReward} and its DTO {@link MQuestGoalRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestGoalRewardMapper extends EntityMapper<MQuestGoalRewardDTO, MQuestGoalReward> {



    default MQuestGoalReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestGoalReward mQuestGoalReward = new MQuestGoalReward();
        mQuestGoalReward.setId(id);
        return mQuestGoalReward;
    }
}

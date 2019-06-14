package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuerillaQuestStageReward} and its DTO {@link MGuerillaQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuerillaQuestStageRewardMapper extends EntityMapper<MGuerillaQuestStageRewardDTO, MGuerillaQuestStageReward> {



    default MGuerillaQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuerillaQuestStageReward mGuerillaQuestStageReward = new MGuerillaQuestStageReward();
        mGuerillaQuestStageReward.setId(id);
        return mGuerillaQuestStageReward;
    }
}

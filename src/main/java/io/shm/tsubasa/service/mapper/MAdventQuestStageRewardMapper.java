package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAdventQuestStageReward} and its DTO {@link MAdventQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MAdventQuestStageRewardMapper extends EntityMapper<MAdventQuestStageRewardDTO, MAdventQuestStageReward> {



    default MAdventQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAdventQuestStageReward mAdventQuestStageReward = new MAdventQuestStageReward();
        mAdventQuestStageReward.setId(id);
        return mAdventQuestStageReward;
    }
}

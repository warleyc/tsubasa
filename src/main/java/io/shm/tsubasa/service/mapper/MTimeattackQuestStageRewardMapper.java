package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTimeattackQuestStageReward} and its DTO {@link MTimeattackQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTimeattackQuestStageRewardMapper extends EntityMapper<MTimeattackQuestStageRewardDTO, MTimeattackQuestStageReward> {



    default MTimeattackQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTimeattackQuestStageReward mTimeattackQuestStageReward = new MTimeattackQuestStageReward();
        mTimeattackQuestStageReward.setId(id);
        return mTimeattackQuestStageReward;
    }
}

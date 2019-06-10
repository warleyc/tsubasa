package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MChallengeQuestStageReward} and its DTO {@link MChallengeQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MChallengeQuestStageRewardMapper extends EntityMapper<MChallengeQuestStageRewardDTO, MChallengeQuestStageReward> {



    default MChallengeQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MChallengeQuestStageReward mChallengeQuestStageReward = new MChallengeQuestStageReward();
        mChallengeQuestStageReward.setId(id);
        return mChallengeQuestStageReward;
    }
}

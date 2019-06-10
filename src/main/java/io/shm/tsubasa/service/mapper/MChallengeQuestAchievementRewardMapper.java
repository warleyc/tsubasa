package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MChallengeQuestAchievementReward} and its DTO {@link MChallengeQuestAchievementRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MChallengeQuestAchievementRewardMapper extends EntityMapper<MChallengeQuestAchievementRewardDTO, MChallengeQuestAchievementReward> {



    default MChallengeQuestAchievementReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MChallengeQuestAchievementReward mChallengeQuestAchievementReward = new MChallengeQuestAchievementReward();
        mChallengeQuestAchievementReward.setId(id);
        return mChallengeQuestAchievementReward;
    }
}

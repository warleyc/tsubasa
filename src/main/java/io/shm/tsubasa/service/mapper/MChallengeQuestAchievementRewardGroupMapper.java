package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MChallengeQuestAchievementRewardGroup} and its DTO {@link MChallengeQuestAchievementRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MChallengeQuestAchievementRewardGroupMapper extends EntityMapper<MChallengeQuestAchievementRewardGroupDTO, MChallengeQuestAchievementRewardGroup> {



    default MChallengeQuestAchievementRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MChallengeQuestAchievementRewardGroup mChallengeQuestAchievementRewardGroup = new MChallengeQuestAchievementRewardGroup();
        mChallengeQuestAchievementRewardGroup.setId(id);
        return mChallengeQuestAchievementRewardGroup;
    }
}

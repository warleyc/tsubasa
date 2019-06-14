package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonAchievementReward} and its DTO {@link MMarathonAchievementRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonAchievementRewardMapper extends EntityMapper<MMarathonAchievementRewardDTO, MMarathonAchievementReward> {



    default MMarathonAchievementReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonAchievementReward mMarathonAchievementReward = new MMarathonAchievementReward();
        mMarathonAchievementReward.setId(id);
        return mMarathonAchievementReward;
    }
}

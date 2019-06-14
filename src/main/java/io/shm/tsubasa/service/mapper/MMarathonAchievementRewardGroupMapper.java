package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonAchievementRewardGroup} and its DTO {@link MMarathonAchievementRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonAchievementRewardGroupMapper extends EntityMapper<MMarathonAchievementRewardGroupDTO, MMarathonAchievementRewardGroup> {



    default MMarathonAchievementRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonAchievementRewardGroup mMarathonAchievementRewardGroup = new MMarathonAchievementRewardGroup();
        mMarathonAchievementRewardGroup.setId(id);
        return mMarathonAchievementRewardGroup;
    }
}

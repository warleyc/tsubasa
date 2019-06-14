package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestAchievementGroup} and its DTO {@link MQuestAchievementGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestAchievementGroupMapper extends EntityMapper<MQuestAchievementGroupDTO, MQuestAchievementGroup> {



    default MQuestAchievementGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestAchievementGroup mQuestAchievementGroup = new MQuestAchievementGroup();
        mQuestAchievementGroup.setId(id);
        return mQuestAchievementGroup;
    }
}

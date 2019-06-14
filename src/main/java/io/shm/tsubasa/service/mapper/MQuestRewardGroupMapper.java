package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestRewardGroup} and its DTO {@link MQuestRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestRewardGroupMapper extends EntityMapper<MQuestRewardGroupDTO, MQuestRewardGroup> {



    default MQuestRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestRewardGroup mQuestRewardGroup = new MQuestRewardGroup();
        mQuestRewardGroup.setId(id);
        return mQuestRewardGroup;
    }
}

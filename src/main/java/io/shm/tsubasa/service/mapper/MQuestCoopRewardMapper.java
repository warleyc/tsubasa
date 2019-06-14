package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestCoopRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestCoopReward} and its DTO {@link MQuestCoopRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestCoopRewardMapper extends EntityMapper<MQuestCoopRewardDTO, MQuestCoopReward> {



    default MQuestCoopReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestCoopReward mQuestCoopReward = new MQuestCoopReward();
        mQuestCoopReward.setId(id);
        return mQuestCoopReward;
    }
}

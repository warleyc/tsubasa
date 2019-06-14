package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestSpecialReward} and its DTO {@link MQuestSpecialRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestSpecialRewardMapper extends EntityMapper<MQuestSpecialRewardDTO, MQuestSpecialReward> {



    default MQuestSpecialReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestSpecialReward mQuestSpecialReward = new MQuestSpecialReward();
        mQuestSpecialReward.setId(id);
        return mQuestSpecialReward;
    }
}

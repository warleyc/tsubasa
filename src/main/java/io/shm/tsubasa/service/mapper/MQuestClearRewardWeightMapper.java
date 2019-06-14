package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestClearRewardWeight} and its DTO {@link MQuestClearRewardWeightDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestClearRewardWeightMapper extends EntityMapper<MQuestClearRewardWeightDTO, MQuestClearRewardWeight> {



    default MQuestClearRewardWeight fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestClearRewardWeight mQuestClearRewardWeight = new MQuestClearRewardWeight();
        mQuestClearRewardWeight.setId(id);
        return mQuestClearRewardWeight;
    }
}

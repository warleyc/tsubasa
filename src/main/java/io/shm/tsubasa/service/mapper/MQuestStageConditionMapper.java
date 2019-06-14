package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestStageConditionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestStageCondition} and its DTO {@link MQuestStageConditionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestStageConditionMapper extends EntityMapper<MQuestStageConditionDTO, MQuestStageCondition> {



    default MQuestStageCondition fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestStageCondition mQuestStageCondition = new MQuestStageCondition();
        mQuestStageCondition.setId(id);
        return mQuestStageCondition;
    }
}

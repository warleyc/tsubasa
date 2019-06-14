package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestStageConditionDescription} and its DTO {@link MQuestStageConditionDescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestStageConditionDescriptionMapper extends EntityMapper<MQuestStageConditionDescriptionDTO, MQuestStageConditionDescription> {



    default MQuestStageConditionDescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestStageConditionDescription mQuestStageConditionDescription = new MQuestStageConditionDescription();
        mQuestStageConditionDescription.setId(id);
        return mQuestStageConditionDescription;
    }
}

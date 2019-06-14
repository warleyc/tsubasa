package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MModelQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MModelQuestStage} and its DTO {@link MModelQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MModelQuestStageMapper extends EntityMapper<MModelQuestStageDTO, MModelQuestStage> {



    default MModelQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MModelQuestStage mModelQuestStage = new MModelQuestStage();
        mModelQuestStage.setId(id);
        return mModelQuestStage;
    }
}

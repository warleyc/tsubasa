package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuerillaQuestStage} and its DTO {@link MGuerillaQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MGuerillaQuestWorldMapper.class})
public interface MGuerillaQuestStageMapper extends EntityMapper<MGuerillaQuestStageDTO, MGuerillaQuestStage> {

    @Mapping(source = "id.id", target = "idId")
    MGuerillaQuestStageDTO toDto(MGuerillaQuestStage mGuerillaQuestStage);

    @Mapping(source = "idId", target = "id")
    MGuerillaQuestStage toEntity(MGuerillaQuestStageDTO mGuerillaQuestStageDTO);

    default MGuerillaQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuerillaQuestStage mGuerillaQuestStage = new MGuerillaQuestStage();
        mGuerillaQuestStage.setId(id);
        return mGuerillaQuestStage;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestStage} and its DTO {@link MQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MQuestWorldMapper.class})
public interface MQuestStageMapper extends EntityMapper<MQuestStageDTO, MQuestStage> {

    @Mapping(source = "id.id", target = "idId")
    MQuestStageDTO toDto(MQuestStage mQuestStage);

    @Mapping(source = "idId", target = "id")
    MQuestStage toEntity(MQuestStageDTO mQuestStageDTO);

    default MQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestStage mQuestStage = new MQuestStage();
        mQuestStage.setId(id);
        return mQuestStage;
    }
}

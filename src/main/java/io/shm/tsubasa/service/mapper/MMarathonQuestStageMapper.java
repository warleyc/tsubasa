package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonQuestStage} and its DTO {@link MMarathonQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MMarathonQuestWorldMapper.class})
public interface MMarathonQuestStageMapper extends EntityMapper<MMarathonQuestStageDTO, MMarathonQuestStage> {

    @Mapping(source = "id.id", target = "idId")
    MMarathonQuestStageDTO toDto(MMarathonQuestStage mMarathonQuestStage);

    @Mapping(source = "idId", target = "id")
    MMarathonQuestStage toEntity(MMarathonQuestStageDTO mMarathonQuestStageDTO);

    default MMarathonQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonQuestStage mMarathonQuestStage = new MMarathonQuestStage();
        mMarathonQuestStage.setId(id);
        return mMarathonQuestStage;
    }
}

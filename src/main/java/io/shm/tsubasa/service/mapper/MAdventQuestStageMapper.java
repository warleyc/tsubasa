package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAdventQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAdventQuestStage} and its DTO {@link MAdventQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MAdventQuestWorldMapper.class})
public interface MAdventQuestStageMapper extends EntityMapper<MAdventQuestStageDTO, MAdventQuestStage> {

    @Mapping(source = "madventquestworld.id", target = "madventquestworldId")
    MAdventQuestStageDTO toDto(MAdventQuestStage mAdventQuestStage);

    @Mapping(source = "madventquestworldId", target = "madventquestworld")
    MAdventQuestStage toEntity(MAdventQuestStageDTO mAdventQuestStageDTO);

    default MAdventQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAdventQuestStage mAdventQuestStage = new MAdventQuestStage();
        mAdventQuestStage.setId(id);
        return mAdventQuestStage;
    }
}

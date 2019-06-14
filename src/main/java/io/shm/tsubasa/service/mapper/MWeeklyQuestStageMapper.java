package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MWeeklyQuestStage} and its DTO {@link MWeeklyQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MWeeklyQuestWorldMapper.class})
public interface MWeeklyQuestStageMapper extends EntityMapper<MWeeklyQuestStageDTO, MWeeklyQuestStage> {

    @Mapping(source = "mweeklyquestworld.id", target = "mweeklyquestworldId")
    MWeeklyQuestStageDTO toDto(MWeeklyQuestStage mWeeklyQuestStage);

    @Mapping(source = "mweeklyquestworldId", target = "mweeklyquestworld")
    MWeeklyQuestStage toEntity(MWeeklyQuestStageDTO mWeeklyQuestStageDTO);

    default MWeeklyQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MWeeklyQuestStage mWeeklyQuestStage = new MWeeklyQuestStage();
        mWeeklyQuestStage.setId(id);
        return mWeeklyQuestStage;
    }
}

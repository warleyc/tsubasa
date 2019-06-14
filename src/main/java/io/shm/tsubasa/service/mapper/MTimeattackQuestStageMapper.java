package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTimeattackQuestStage} and its DTO {@link MTimeattackQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MTimeattackQuestWorldMapper.class})
public interface MTimeattackQuestStageMapper extends EntityMapper<MTimeattackQuestStageDTO, MTimeattackQuestStage> {

    @Mapping(source = "mtimeattackquestworld.id", target = "mtimeattackquestworldId")
    MTimeattackQuestStageDTO toDto(MTimeattackQuestStage mTimeattackQuestStage);

    @Mapping(source = "mtimeattackquestworldId", target = "mtimeattackquestworld")
    MTimeattackQuestStage toEntity(MTimeattackQuestStageDTO mTimeattackQuestStageDTO);

    default MTimeattackQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTimeattackQuestStage mTimeattackQuestStage = new MTimeattackQuestStage();
        mTimeattackQuestStage.setId(id);
        return mTimeattackQuestStage;
    }
}

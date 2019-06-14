package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLuckWeeklyQuestStage} and its DTO {@link MLuckWeeklyQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MLuckWeeklyQuestWorldMapper.class})
public interface MLuckWeeklyQuestStageMapper extends EntityMapper<MLuckWeeklyQuestStageDTO, MLuckWeeklyQuestStage> {

    @Mapping(source = "id.id", target = "idId")
    MLuckWeeklyQuestStageDTO toDto(MLuckWeeklyQuestStage mLuckWeeklyQuestStage);

    @Mapping(source = "idId", target = "id")
    MLuckWeeklyQuestStage toEntity(MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO);

    default MLuckWeeklyQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLuckWeeklyQuestStage mLuckWeeklyQuestStage = new MLuckWeeklyQuestStage();
        mLuckWeeklyQuestStage.setId(id);
        return mLuckWeeklyQuestStage;
    }
}

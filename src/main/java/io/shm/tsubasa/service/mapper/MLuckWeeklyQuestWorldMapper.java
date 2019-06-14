package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLuckWeeklyQuestWorld} and its DTO {@link MLuckWeeklyQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLuckWeeklyQuestWorldMapper extends EntityMapper<MLuckWeeklyQuestWorldDTO, MLuckWeeklyQuestWorld> {


    @Mapping(target = "mLuckWeeklyQuestStages", ignore = true)
    MLuckWeeklyQuestWorld toEntity(MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO);

    default MLuckWeeklyQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld = new MLuckWeeklyQuestWorld();
        mLuckWeeklyQuestWorld.setId(id);
        return mLuckWeeklyQuestWorld;
    }
}

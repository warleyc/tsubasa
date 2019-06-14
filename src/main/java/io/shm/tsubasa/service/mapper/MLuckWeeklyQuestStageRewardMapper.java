package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLuckWeeklyQuestStageReward} and its DTO {@link MLuckWeeklyQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLuckWeeklyQuestStageRewardMapper extends EntityMapper<MLuckWeeklyQuestStageRewardDTO, MLuckWeeklyQuestStageReward> {



    default MLuckWeeklyQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLuckWeeklyQuestStageReward mLuckWeeklyQuestStageReward = new MLuckWeeklyQuestStageReward();
        mLuckWeeklyQuestStageReward.setId(id);
        return mLuckWeeklyQuestStageReward;
    }
}

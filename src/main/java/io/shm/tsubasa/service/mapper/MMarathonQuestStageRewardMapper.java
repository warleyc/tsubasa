package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonQuestStageReward} and its DTO {@link MMarathonQuestStageRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonQuestStageRewardMapper extends EntityMapper<MMarathonQuestStageRewardDTO, MMarathonQuestStageReward> {



    default MMarathonQuestStageReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonQuestStageReward mMarathonQuestStageReward = new MMarathonQuestStageReward();
        mMarathonQuestStageReward.setId(id);
        return mMarathonQuestStageReward;
    }
}

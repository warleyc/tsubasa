package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MWeeklyQuestTsubasaPointReward} and its DTO {@link MWeeklyQuestTsubasaPointRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MWeeklyQuestTsubasaPointRewardMapper extends EntityMapper<MWeeklyQuestTsubasaPointRewardDTO, MWeeklyQuestTsubasaPointReward> {



    default MWeeklyQuestTsubasaPointReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MWeeklyQuestTsubasaPointReward mWeeklyQuestTsubasaPointReward = new MWeeklyQuestTsubasaPointReward();
        mWeeklyQuestTsubasaPointReward.setId(id);
        return mWeeklyQuestTsubasaPointReward;
    }
}

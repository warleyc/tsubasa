package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonQuestTsubasaPointReward} and its DTO {@link MMarathonQuestTsubasaPointRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonQuestTsubasaPointRewardMapper extends EntityMapper<MMarathonQuestTsubasaPointRewardDTO, MMarathonQuestTsubasaPointReward> {



    default MMarathonQuestTsubasaPointReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonQuestTsubasaPointReward mMarathonQuestTsubasaPointReward = new MMarathonQuestTsubasaPointReward();
        mMarathonQuestTsubasaPointReward.setId(id);
        return mMarathonQuestTsubasaPointReward;
    }
}

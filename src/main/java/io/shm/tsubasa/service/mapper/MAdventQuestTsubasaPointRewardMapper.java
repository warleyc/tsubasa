package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAdventQuestTsubasaPointReward} and its DTO {@link MAdventQuestTsubasaPointRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MAdventQuestTsubasaPointRewardMapper extends EntityMapper<MAdventQuestTsubasaPointRewardDTO, MAdventQuestTsubasaPointReward> {



    default MAdventQuestTsubasaPointReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAdventQuestTsubasaPointReward mAdventQuestTsubasaPointReward = new MAdventQuestTsubasaPointReward();
        mAdventQuestTsubasaPointReward.setId(id);
        return mAdventQuestTsubasaPointReward;
    }
}

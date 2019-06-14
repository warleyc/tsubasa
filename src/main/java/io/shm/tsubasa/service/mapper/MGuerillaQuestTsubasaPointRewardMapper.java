package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuerillaQuestTsubasaPointReward} and its DTO {@link MGuerillaQuestTsubasaPointRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuerillaQuestTsubasaPointRewardMapper extends EntityMapper<MGuerillaQuestTsubasaPointRewardDTO, MGuerillaQuestTsubasaPointReward> {



    default MGuerillaQuestTsubasaPointReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuerillaQuestTsubasaPointReward mGuerillaQuestTsubasaPointReward = new MGuerillaQuestTsubasaPointReward();
        mGuerillaQuestTsubasaPointReward.setId(id);
        return mGuerillaQuestTsubasaPointReward;
    }
}

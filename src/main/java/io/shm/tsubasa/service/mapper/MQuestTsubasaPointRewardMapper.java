package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestTsubasaPointReward} and its DTO {@link MQuestTsubasaPointRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestTsubasaPointRewardMapper extends EntityMapper<MQuestTsubasaPointRewardDTO, MQuestTsubasaPointReward> {



    default MQuestTsubasaPointReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestTsubasaPointReward mQuestTsubasaPointReward = new MQuestTsubasaPointReward();
        mQuestTsubasaPointReward.setId(id);
        return mQuestTsubasaPointReward;
    }
}

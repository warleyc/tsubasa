package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonLoopReward} and its DTO {@link MMarathonLoopRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonLoopRewardMapper extends EntityMapper<MMarathonLoopRewardDTO, MMarathonLoopReward> {



    default MMarathonLoopReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonLoopReward mMarathonLoopReward = new MMarathonLoopReward();
        mMarathonLoopReward.setId(id);
        return mMarathonLoopReward;
    }
}

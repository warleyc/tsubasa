package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonLoopRewardGroup} and its DTO {@link MMarathonLoopRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonLoopRewardGroupMapper extends EntityMapper<MMarathonLoopRewardGroupDTO, MMarathonLoopRewardGroup> {



    default MMarathonLoopRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonLoopRewardGroup mMarathonLoopRewardGroup = new MMarathonLoopRewardGroup();
        mMarathonLoopRewardGroup.setId(id);
        return mMarathonLoopRewardGroup;
    }
}

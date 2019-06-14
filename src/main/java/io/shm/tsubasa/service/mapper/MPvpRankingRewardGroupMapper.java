package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpRankingRewardGroup} and its DTO {@link MPvpRankingRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPvpRankingRewardGroupMapper extends EntityMapper<MPvpRankingRewardGroupDTO, MPvpRankingRewardGroup> {



    default MPvpRankingRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpRankingRewardGroup mPvpRankingRewardGroup = new MPvpRankingRewardGroup();
        mPvpRankingRewardGroup.setId(id);
        return mPvpRankingRewardGroup;
    }
}

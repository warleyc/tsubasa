package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonRankingRewardGroup} and its DTO {@link MMarathonRankingRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonRankingRewardGroupMapper extends EntityMapper<MMarathonRankingRewardGroupDTO, MMarathonRankingRewardGroup> {



    default MMarathonRankingRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonRankingRewardGroup mMarathonRankingRewardGroup = new MMarathonRankingRewardGroup();
        mMarathonRankingRewardGroup.setId(id);
        return mMarathonRankingRewardGroup;
    }
}

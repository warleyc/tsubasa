package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTimeattackRankingRewardGroup} and its DTO {@link MTimeattackRankingRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTimeattackRankingRewardGroupMapper extends EntityMapper<MTimeattackRankingRewardGroupDTO, MTimeattackRankingRewardGroup> {



    default MTimeattackRankingRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTimeattackRankingRewardGroup mTimeattackRankingRewardGroup = new MTimeattackRankingRewardGroup();
        mTimeattackRankingRewardGroup.setId(id);
        return mTimeattackRankingRewardGroup;
    }
}

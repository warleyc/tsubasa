package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLeagueRankingRewardGroup} and its DTO {@link MLeagueRankingRewardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLeagueRankingRewardGroupMapper extends EntityMapper<MLeagueRankingRewardGroupDTO, MLeagueRankingRewardGroup> {



    default MLeagueRankingRewardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLeagueRankingRewardGroup mLeagueRankingRewardGroup = new MLeagueRankingRewardGroup();
        mLeagueRankingRewardGroup.setId(id);
        return mLeagueRankingRewardGroup;
    }
}

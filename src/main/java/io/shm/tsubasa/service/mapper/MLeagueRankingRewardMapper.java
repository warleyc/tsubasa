package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLeagueRankingReward} and its DTO {@link MLeagueRankingRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLeagueRankingRewardMapper extends EntityMapper<MLeagueRankingRewardDTO, MLeagueRankingReward> {



    default MLeagueRankingReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLeagueRankingReward mLeagueRankingReward = new MLeagueRankingReward();
        mLeagueRankingReward.setId(id);
        return mLeagueRankingReward;
    }
}

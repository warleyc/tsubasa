package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MRegulatedLeagueRankingReward} and its DTO {@link MRegulatedLeagueRankingRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MRegulatedLeagueRankingRewardMapper extends EntityMapper<MRegulatedLeagueRankingRewardDTO, MRegulatedLeagueRankingReward> {



    default MRegulatedLeagueRankingReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MRegulatedLeagueRankingReward mRegulatedLeagueRankingReward = new MRegulatedLeagueRankingReward();
        mRegulatedLeagueRankingReward.setId(id);
        return mRegulatedLeagueRankingReward;
    }
}

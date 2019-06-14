package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTimeattackRankingReward} and its DTO {@link MTimeattackRankingRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTimeattackRankingRewardMapper extends EntityMapper<MTimeattackRankingRewardDTO, MTimeattackRankingReward> {



    default MTimeattackRankingReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTimeattackRankingReward mTimeattackRankingReward = new MTimeattackRankingReward();
        mTimeattackRankingReward.setId(id);
        return mTimeattackRankingReward;
    }
}

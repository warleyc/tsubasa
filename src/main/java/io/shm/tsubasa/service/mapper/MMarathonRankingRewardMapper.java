package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonRankingReward} and its DTO {@link MMarathonRankingRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonRankingRewardMapper extends EntityMapper<MMarathonRankingRewardDTO, MMarathonRankingReward> {



    default MMarathonRankingReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonRankingReward mMarathonRankingReward = new MMarathonRankingReward();
        mMarathonRankingReward.setId(id);
        return mMarathonRankingReward;
    }
}

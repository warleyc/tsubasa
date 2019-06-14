package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLeagueAffiliateReward} and its DTO {@link MLeagueAffiliateRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLeagueAffiliateRewardMapper extends EntityMapper<MLeagueAffiliateRewardDTO, MLeagueAffiliateReward> {



    default MLeagueAffiliateReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLeagueAffiliateReward mLeagueAffiliateReward = new MLeagueAffiliateReward();
        mLeagueAffiliateReward.setId(id);
        return mLeagueAffiliateReward;
    }
}

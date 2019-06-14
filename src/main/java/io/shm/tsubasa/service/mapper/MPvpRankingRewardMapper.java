package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpRankingRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpRankingReward} and its DTO {@link MPvpRankingRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {MPvpWaveMapper.class})
public interface MPvpRankingRewardMapper extends EntityMapper<MPvpRankingRewardDTO, MPvpRankingReward> {

    @Mapping(source = "mpvpwave.id", target = "mpvpwaveId")
    MPvpRankingRewardDTO toDto(MPvpRankingReward mPvpRankingReward);

    @Mapping(source = "mpvpwaveId", target = "mpvpwave")
    MPvpRankingReward toEntity(MPvpRankingRewardDTO mPvpRankingRewardDTO);

    default MPvpRankingReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpRankingReward mPvpRankingReward = new MPvpRankingReward();
        mPvpRankingReward.setId(id);
        return mPvpRankingReward;
    }
}

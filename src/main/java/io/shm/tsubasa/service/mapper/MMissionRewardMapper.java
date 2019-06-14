package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMissionRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMissionReward} and its DTO {@link MMissionRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMissionRewardMapper extends EntityMapper<MMissionRewardDTO, MMissionReward> {


    @Mapping(target = "mGuildMissions", ignore = true)
    @Mapping(target = "mMissions", ignore = true)
    MMissionReward toEntity(MMissionRewardDTO mMissionRewardDTO);

    default MMissionReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMissionReward mMissionReward = new MMissionReward();
        mMissionReward.setId(id);
        return mMissionReward;
    }
}

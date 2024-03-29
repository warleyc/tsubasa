package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMission} and its DTO {@link MMissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MMissionRewardMapper.class})
public interface MMissionMapper extends EntityMapper<MMissionDTO, MMission> {

    @Mapping(source = "mmissionreward.id", target = "mmissionrewardId")
    MMissionDTO toDto(MMission mMission);

    @Mapping(source = "mmissionrewardId", target = "mmissionreward")
    @Mapping(target = "mAchievements", ignore = true)
    MMission toEntity(MMissionDTO mMissionDTO);

    default MMission fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMission mMission = new MMission();
        mMission.setId(id);
        return mMission;
    }
}

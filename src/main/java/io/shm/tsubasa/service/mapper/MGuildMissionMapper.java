package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildMissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildMission} and its DTO {@link MGuildMissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MMissionRewardMapper.class})
public interface MGuildMissionMapper extends EntityMapper<MGuildMissionDTO, MGuildMission> {

    @Mapping(source = "id.id", target = "idId")
    MGuildMissionDTO toDto(MGuildMission mGuildMission);

    @Mapping(source = "idId", target = "id")
    MGuildMission toEntity(MGuildMissionDTO mGuildMissionDTO);

    default MGuildMission fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildMission mGuildMission = new MGuildMission();
        mGuildMission.setId(id);
        return mGuildMission;
    }
}

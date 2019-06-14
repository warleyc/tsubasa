package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetTeamGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetTeamGroup} and its DTO {@link MTargetTeamGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {MTeamMapper.class})
public interface MTargetTeamGroupMapper extends EntityMapper<MTargetTeamGroupDTO, MTargetTeamGroup> {

    @Mapping(source = "id.id", target = "idId")
    MTargetTeamGroupDTO toDto(MTargetTeamGroup mTargetTeamGroup);

    @Mapping(source = "idId", target = "id")
    MTargetTeamGroup toEntity(MTargetTeamGroupDTO mTargetTeamGroupDTO);

    default MTargetTeamGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetTeamGroup mTargetTeamGroup = new MTargetTeamGroup();
        mTargetTeamGroup.setId(id);
        return mTargetTeamGroup;
    }
}

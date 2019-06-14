package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetFormationGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetFormationGroup} and its DTO {@link MTargetFormationGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {MFormationMapper.class})
public interface MTargetFormationGroupMapper extends EntityMapper<MTargetFormationGroupDTO, MTargetFormationGroup> {

    @Mapping(source = "id.id", target = "idId")
    MTargetFormationGroupDTO toDto(MTargetFormationGroup mTargetFormationGroup);

    @Mapping(source = "idId", target = "id")
    MTargetFormationGroup toEntity(MTargetFormationGroupDTO mTargetFormationGroupDTO);

    default MTargetFormationGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetFormationGroup mTargetFormationGroup = new MTargetFormationGroup();
        mTargetFormationGroup.setId(id);
        return mTargetFormationGroup;
    }
}

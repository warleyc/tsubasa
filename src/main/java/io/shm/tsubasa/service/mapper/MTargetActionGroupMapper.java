package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetActionGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetActionGroup} and its DTO {@link MTargetActionGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {MActionMapper.class})
public interface MTargetActionGroupMapper extends EntityMapper<MTargetActionGroupDTO, MTargetActionGroup> {

    @Mapping(source = "maction.id", target = "mactionId")
    MTargetActionGroupDTO toDto(MTargetActionGroup mTargetActionGroup);

    @Mapping(source = "mactionId", target = "maction")
    MTargetActionGroup toEntity(MTargetActionGroupDTO mTargetActionGroupDTO);

    default MTargetActionGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetActionGroup mTargetActionGroup = new MTargetActionGroup();
        mTargetActionGroup.setId(id);
        return mTargetActionGroup;
    }
}

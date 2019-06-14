package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetActionTypeGroup} and its DTO {@link MTargetActionTypeGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTargetActionTypeGroupMapper extends EntityMapper<MTargetActionTypeGroupDTO, MTargetActionTypeGroup> {



    default MTargetActionTypeGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetActionTypeGroup mTargetActionTypeGroup = new MTargetActionTypeGroup();
        mTargetActionTypeGroup.setId(id);
        return mTargetActionTypeGroup;
    }
}

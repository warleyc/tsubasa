package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetRarityGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetRarityGroup} and its DTO {@link MTargetRarityGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTargetRarityGroupMapper extends EntityMapper<MTargetRarityGroupDTO, MTargetRarityGroup> {



    default MTargetRarityGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetRarityGroup mTargetRarityGroup = new MTargetRarityGroup();
        mTargetRarityGroup.setId(id);
        return mTargetRarityGroup;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MEncountersCommandBranch} and its DTO {@link MEncountersCommandBranchDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MEncountersCommandBranchMapper extends EntityMapper<MEncountersCommandBranchDTO, MEncountersCommandBranch> {



    default MEncountersCommandBranch fromId(Long id) {
        if (id == null) {
            return null;
        }
        MEncountersCommandBranch mEncountersCommandBranch = new MEncountersCommandBranch();
        mEncountersCommandBranch.setId(id);
        return mEncountersCommandBranch;
    }
}

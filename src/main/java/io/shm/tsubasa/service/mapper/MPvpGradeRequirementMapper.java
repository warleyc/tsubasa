package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpGradeRequirement} and its DTO {@link MPvpGradeRequirementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPvpGradeRequirementMapper extends EntityMapper<MPvpGradeRequirementDTO, MPvpGradeRequirement> {



    default MPvpGradeRequirement fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpGradeRequirement mPvpGradeRequirement = new MPvpGradeRequirement();
        mPvpGradeRequirement.setId(id);
        return mPvpGradeRequirement;
    }
}

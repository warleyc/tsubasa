package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDistributeCardParameterStep} and its DTO {@link MDistributeCardParameterStepDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDistributeCardParameterStepMapper extends EntityMapper<MDistributeCardParameterStepDTO, MDistributeCardParameterStep> {



    default MDistributeCardParameterStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDistributeCardParameterStep mDistributeCardParameterStep = new MDistributeCardParameterStep();
        mDistributeCardParameterStep.setId(id);
        return mDistributeCardParameterStep;
    }
}

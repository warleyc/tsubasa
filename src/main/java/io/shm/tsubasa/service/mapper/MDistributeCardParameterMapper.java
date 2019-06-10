package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDistributeCardParameterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDistributeCardParameter} and its DTO {@link MDistributeCardParameterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDistributeCardParameterMapper extends EntityMapper<MDistributeCardParameterDTO, MDistributeCardParameter> {



    default MDistributeCardParameter fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDistributeCardParameter mDistributeCardParameter = new MDistributeCardParameter();
        mDistributeCardParameter.setId(id);
        return mDistributeCardParameter;
    }
}

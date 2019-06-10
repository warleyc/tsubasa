package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDistributeParamPointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDistributeParamPoint} and its DTO {@link MDistributeParamPointDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDistributeParamPointMapper extends EntityMapper<MDistributeParamPointDTO, MDistributeParamPoint> {



    default MDistributeParamPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDistributeParamPoint mDistributeParamPoint = new MDistributeParamPoint();
        mDistributeParamPoint.setId(id);
        return mDistributeParamPoint;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLuckRateGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLuckRateGroup} and its DTO {@link MLuckRateGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLuckRateGroupMapper extends EntityMapper<MLuckRateGroupDTO, MLuckRateGroup> {



    default MLuckRateGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLuckRateGroup mLuckRateGroup = new MLuckRateGroup();
        mLuckRateGroup.setId(id);
        return mLuckRateGroup;
    }
}

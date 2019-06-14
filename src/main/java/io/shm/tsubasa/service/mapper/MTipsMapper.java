package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTipsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTips} and its DTO {@link MTipsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTipsMapper extends EntityMapper<MTipsDTO, MTips> {



    default MTips fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTips mTips = new MTips();
        mTips.setId(id);
        return mTips;
    }
}

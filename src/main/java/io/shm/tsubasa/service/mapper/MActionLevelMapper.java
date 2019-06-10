package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MActionLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MActionLevel} and its DTO {@link MActionLevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MActionLevelMapper extends EntityMapper<MActionLevelDTO, MActionLevel> {



    default MActionLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        MActionLevel mActionLevel = new MActionLevel();
        mActionLevel.setId(id);
        return mActionLevel;
    }
}

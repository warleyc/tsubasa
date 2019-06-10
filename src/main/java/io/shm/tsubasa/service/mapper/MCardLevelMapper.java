package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCardLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCardLevel} and its DTO {@link MCardLevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCardLevelMapper extends EntityMapper<MCardLevelDTO, MCardLevel> {



    default MCardLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCardLevel mCardLevel = new MCardLevel();
        mCardLevel.setId(id);
        return mCardLevel;
    }
}

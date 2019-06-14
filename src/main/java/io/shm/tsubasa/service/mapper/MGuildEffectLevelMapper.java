package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildEffectLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildEffectLevel} and its DTO {@link MGuildEffectLevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuildEffectLevelMapper extends EntityMapper<MGuildEffectLevelDTO, MGuildEffectLevel> {



    default MGuildEffectLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildEffectLevel mGuildEffectLevel = new MGuildEffectLevel();
        mGuildEffectLevel.setId(id);
        return mGuildEffectLevel;
    }
}

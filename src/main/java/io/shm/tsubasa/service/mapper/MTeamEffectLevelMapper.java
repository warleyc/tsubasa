package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTeamEffectLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTeamEffectLevel} and its DTO {@link MTeamEffectLevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTeamEffectLevelMapper extends EntityMapper<MTeamEffectLevelDTO, MTeamEffectLevel> {



    default MTeamEffectLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTeamEffectLevel mTeamEffectLevel = new MTeamEffectLevel();
        mTeamEffectLevel.setId(id);
        return mTeamEffectLevel;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLeagueEffectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLeagueEffect} and its DTO {@link MLeagueEffectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLeagueEffectMapper extends EntityMapper<MLeagueEffectDTO, MLeagueEffect> {



    default MLeagueEffect fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLeagueEffect mLeagueEffect = new MLeagueEffect();
        mLeagueEffect.setId(id);
        return mLeagueEffect;
    }
}

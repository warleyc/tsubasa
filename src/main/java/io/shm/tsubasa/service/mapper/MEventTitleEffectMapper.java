package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MEventTitleEffectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MEventTitleEffect} and its DTO {@link MEventTitleEffectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MEventTitleEffectMapper extends EntityMapper<MEventTitleEffectDTO, MEventTitleEffect> {



    default MEventTitleEffect fromId(Long id) {
        if (id == null) {
            return null;
        }
        MEventTitleEffect mEventTitleEffect = new MEventTitleEffect();
        mEventTitleEffect.setId(id);
        return mEventTitleEffect;
    }
}

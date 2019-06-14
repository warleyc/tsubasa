package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildEffectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildEffect} and its DTO {@link MGuildEffectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuildEffectMapper extends EntityMapper<MGuildEffectDTO, MGuildEffect> {



    default MGuildEffect fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildEffect mGuildEffect = new MGuildEffect();
        mGuildEffect.setId(id);
        return mGuildEffect;
    }
}

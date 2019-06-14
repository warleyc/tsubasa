package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMedalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMedal} and its DTO {@link MMedalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMedalMapper extends EntityMapper<MMedalDTO, MMedal> {



    default MMedal fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMedal mMedal = new MMedal();
        mMedal.setId(id);
        return mMedal;
    }
}

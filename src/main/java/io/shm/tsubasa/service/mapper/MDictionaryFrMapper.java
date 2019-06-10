package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryFrDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryFr} and its DTO {@link MDictionaryFrDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryFrMapper extends EntityMapper<MDictionaryFrDTO, MDictionaryFr> {



    default MDictionaryFr fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryFr mDictionaryFr = new MDictionaryFr();
        mDictionaryFr.setId(id);
        return mDictionaryFr;
    }
}

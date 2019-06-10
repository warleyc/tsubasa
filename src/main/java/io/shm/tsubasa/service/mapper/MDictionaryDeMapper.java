package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryDeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryDe} and its DTO {@link MDictionaryDeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryDeMapper extends EntityMapper<MDictionaryDeDTO, MDictionaryDe> {



    default MDictionaryDe fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryDe mDictionaryDe = new MDictionaryDe();
        mDictionaryDe.setId(id);
        return mDictionaryDe;
    }
}

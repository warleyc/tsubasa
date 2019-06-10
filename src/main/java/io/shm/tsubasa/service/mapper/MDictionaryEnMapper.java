package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryEnDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryEn} and its DTO {@link MDictionaryEnDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryEnMapper extends EntityMapper<MDictionaryEnDTO, MDictionaryEn> {



    default MDictionaryEn fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryEn mDictionaryEn = new MDictionaryEn();
        mDictionaryEn.setId(id);
        return mDictionaryEn;
    }
}

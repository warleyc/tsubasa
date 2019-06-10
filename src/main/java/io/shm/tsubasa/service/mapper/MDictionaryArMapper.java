package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryArDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryAr} and its DTO {@link MDictionaryArDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryArMapper extends EntityMapper<MDictionaryArDTO, MDictionaryAr> {



    default MDictionaryAr fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryAr mDictionaryAr = new MDictionaryAr();
        mDictionaryAr.setId(id);
        return mDictionaryAr;
    }
}

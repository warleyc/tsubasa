package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryJaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryJa} and its DTO {@link MDictionaryJaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryJaMapper extends EntityMapper<MDictionaryJaDTO, MDictionaryJa> {



    default MDictionaryJa fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryJa mDictionaryJa = new MDictionaryJa();
        mDictionaryJa.setId(id);
        return mDictionaryJa;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryEsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryEs} and its DTO {@link MDictionaryEsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryEsMapper extends EntityMapper<MDictionaryEsDTO, MDictionaryEs> {



    default MDictionaryEs fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryEs mDictionaryEs = new MDictionaryEs();
        mDictionaryEs.setId(id);
        return mDictionaryEs;
    }
}

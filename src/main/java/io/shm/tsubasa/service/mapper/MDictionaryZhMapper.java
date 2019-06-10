package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryZhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryZh} and its DTO {@link MDictionaryZhDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryZhMapper extends EntityMapper<MDictionaryZhDTO, MDictionaryZh> {



    default MDictionaryZh fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryZh mDictionaryZh = new MDictionaryZh();
        mDictionaryZh.setId(id);
        return mDictionaryZh;
    }
}

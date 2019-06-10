package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryItDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryIt} and its DTO {@link MDictionaryItDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryItMapper extends EntityMapper<MDictionaryItDTO, MDictionaryIt> {



    default MDictionaryIt fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryIt mDictionaryIt = new MDictionaryIt();
        mDictionaryIt.setId(id);
        return mDictionaryIt;
    }
}

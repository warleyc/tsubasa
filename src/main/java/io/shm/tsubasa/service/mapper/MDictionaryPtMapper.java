package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDictionaryPtDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDictionaryPt} and its DTO {@link MDictionaryPtDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDictionaryPtMapper extends EntityMapper<MDictionaryPtDTO, MDictionaryPt> {



    default MDictionaryPt fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDictionaryPt mDictionaryPt = new MDictionaryPt();
        mDictionaryPt.setId(id);
        return mDictionaryPt;
    }
}

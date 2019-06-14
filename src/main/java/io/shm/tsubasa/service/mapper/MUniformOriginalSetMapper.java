package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MUniformOriginalSetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MUniformOriginalSet} and its DTO {@link MUniformOriginalSetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MUniformOriginalSetMapper extends EntityMapper<MUniformOriginalSetDTO, MUniformOriginalSet> {



    default MUniformOriginalSet fromId(Long id) {
        if (id == null) {
            return null;
        }
        MUniformOriginalSet mUniformOriginalSet = new MUniformOriginalSet();
        mUniformOriginalSet.setId(id);
        return mUniformOriginalSet;
    }
}

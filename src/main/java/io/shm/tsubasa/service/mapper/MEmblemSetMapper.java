package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MEmblemSetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MEmblemSet} and its DTO {@link MEmblemSetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MEmblemSetMapper extends EntityMapper<MEmblemSetDTO, MEmblemSet> {



    default MEmblemSet fromId(Long id) {
        if (id == null) {
            return null;
        }
        MEmblemSet mEmblemSet = new MEmblemSet();
        mEmblemSet.setId(id);
        return mEmblemSet;
    }
}

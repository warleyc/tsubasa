package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDeckConditionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDeckCondition} and its DTO {@link MDeckConditionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDeckConditionMapper extends EntityMapper<MDeckConditionDTO, MDeckCondition> {



    default MDeckCondition fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDeckCondition mDeckCondition = new MDeckCondition();
        mDeckCondition.setId(id);
        return mDeckCondition;
    }
}

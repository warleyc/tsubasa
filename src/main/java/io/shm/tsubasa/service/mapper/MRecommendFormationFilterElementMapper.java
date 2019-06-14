package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MRecommendFormationFilterElement} and its DTO {@link MRecommendFormationFilterElementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MRecommendFormationFilterElementMapper extends EntityMapper<MRecommendFormationFilterElementDTO, MRecommendFormationFilterElement> {



    default MRecommendFormationFilterElement fromId(Long id) {
        if (id == null) {
            return null;
        }
        MRecommendFormationFilterElement mRecommendFormationFilterElement = new MRecommendFormationFilterElement();
        mRecommendFormationFilterElement.setId(id);
        return mRecommendFormationFilterElement;
    }
}

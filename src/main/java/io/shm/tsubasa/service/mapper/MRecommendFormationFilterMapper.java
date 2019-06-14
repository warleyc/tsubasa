package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MRecommendFormationFilter} and its DTO {@link MRecommendFormationFilterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MRecommendFormationFilterMapper extends EntityMapper<MRecommendFormationFilterDTO, MRecommendFormationFilter> {



    default MRecommendFormationFilter fromId(Long id) {
        if (id == null) {
            return null;
        }
        MRecommendFormationFilter mRecommendFormationFilter = new MRecommendFormationFilter();
        mRecommendFormationFilter.setId(id);
        return mRecommendFormationFilter;
    }
}

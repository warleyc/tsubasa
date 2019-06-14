package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MStoryResourceMappingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MStoryResourceMapping} and its DTO {@link MStoryResourceMappingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MStoryResourceMappingMapper extends EntityMapper<MStoryResourceMappingDTO, MStoryResourceMapping> {



    default MStoryResourceMapping fromId(Long id) {
        if (id == null) {
            return null;
        }
        MStoryResourceMapping mStoryResourceMapping = new MStoryResourceMapping();
        mStoryResourceMapping.setId(id);
        return mStoryResourceMapping;
    }
}

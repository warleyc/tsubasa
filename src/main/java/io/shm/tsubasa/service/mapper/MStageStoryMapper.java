package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MStageStoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MStageStory} and its DTO {@link MStageStoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MStageStoryMapper extends EntityMapper<MStageStoryDTO, MStageStory> {



    default MStageStory fromId(Long id) {
        if (id == null) {
            return null;
        }
        MStageStory mStageStory = new MStageStory();
        mStageStory.setId(id);
        return mStageStory;
    }
}

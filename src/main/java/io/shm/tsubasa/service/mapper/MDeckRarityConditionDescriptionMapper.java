package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDeckRarityConditionDescription} and its DTO {@link MDeckRarityConditionDescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDeckRarityConditionDescriptionMapper extends EntityMapper<MDeckRarityConditionDescriptionDTO, MDeckRarityConditionDescription> {



    default MDeckRarityConditionDescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDeckRarityConditionDescription mDeckRarityConditionDescription = new MDeckRarityConditionDescription();
        mDeckRarityConditionDescription.setId(id);
        return mDeckRarityConditionDescription;
    }
}

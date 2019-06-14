package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MStoreReviewUrlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MStoreReviewUrl} and its DTO {@link MStoreReviewUrlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MStoreReviewUrlMapper extends EntityMapper<MStoreReviewUrlDTO, MStoreReviewUrl> {



    default MStoreReviewUrl fromId(Long id) {
        if (id == null) {
            return null;
        }
        MStoreReviewUrl mStoreReviewUrl = new MStoreReviewUrl();
        mStoreReviewUrl.setId(id);
        return mStoreReviewUrl;
    }
}

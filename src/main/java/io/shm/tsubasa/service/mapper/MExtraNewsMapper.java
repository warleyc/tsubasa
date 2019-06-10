package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MExtraNewsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MExtraNews} and its DTO {@link MExtraNewsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MExtraNewsMapper extends EntityMapper<MExtraNewsDTO, MExtraNews> {



    default MExtraNews fromId(Long id) {
        if (id == null) {
            return null;
        }
        MExtraNews mExtraNews = new MExtraNews();
        mExtraNews.setId(id);
        return mExtraNews;
    }
}

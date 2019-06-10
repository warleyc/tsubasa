package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionSwipeIcon} and its DTO {@link MGachaRenditionSwipeIconDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionSwipeIconMapper extends EntityMapper<MGachaRenditionSwipeIconDTO, MGachaRenditionSwipeIcon> {



    default MGachaRenditionSwipeIcon fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionSwipeIcon mGachaRenditionSwipeIcon = new MGachaRenditionSwipeIcon();
        mGachaRenditionSwipeIcon.setId(id);
        return mGachaRenditionSwipeIcon;
    }
}

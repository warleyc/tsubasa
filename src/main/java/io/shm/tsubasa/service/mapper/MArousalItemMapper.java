package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MArousalItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MArousalItem} and its DTO {@link MArousalItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MArousalItemMapper extends EntityMapper<MArousalItemDTO, MArousalItem> {



    default MArousalItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MArousalItem mArousalItem = new MArousalItem();
        mArousalItem.setId(id);
        return mArousalItem;
    }
}

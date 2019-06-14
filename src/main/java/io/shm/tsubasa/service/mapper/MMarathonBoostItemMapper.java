package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonBoostItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonBoostItem} and its DTO {@link MMarathonBoostItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonBoostItemMapper extends EntityMapper<MMarathonBoostItemDTO, MMarathonBoostItem> {



    default MMarathonBoostItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonBoostItem mMarathonBoostItem = new MMarathonBoostItem();
        mMarathonBoostItem.setId(id);
        return mMarathonBoostItem;
    }
}

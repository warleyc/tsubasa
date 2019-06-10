package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MApRecoveryItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MApRecoveryItem} and its DTO {@link MApRecoveryItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MApRecoveryItemMapper extends EntityMapper<MApRecoveryItemDTO, MApRecoveryItem> {



    default MApRecoveryItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MApRecoveryItem mApRecoveryItem = new MApRecoveryItem();
        mApRecoveryItem.setId(id);
        return mApRecoveryItem;
    }
}

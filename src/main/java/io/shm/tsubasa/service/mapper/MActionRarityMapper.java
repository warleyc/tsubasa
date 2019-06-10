package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MActionRarityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MActionRarity} and its DTO {@link MActionRarityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MActionRarityMapper extends EntityMapper<MActionRarityDTO, MActionRarity> {



    default MActionRarity fromId(Long id) {
        if (id == null) {
            return null;
        }
        MActionRarity mActionRarity = new MActionRarity();
        mActionRarity.setId(id);
        return mActionRarity;
    }
}

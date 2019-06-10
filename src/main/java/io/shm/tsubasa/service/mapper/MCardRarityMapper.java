package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCardRarityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCardRarity} and its DTO {@link MCardRarityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCardRarityMapper extends EntityMapper<MCardRarityDTO, MCardRarity> {



    default MCardRarity fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCardRarity mCardRarity = new MCardRarity();
        mCardRarity.setId(id);
        return mCardRarity;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTeamEffectRarityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTeamEffectRarity} and its DTO {@link MTeamEffectRarityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTeamEffectRarityMapper extends EntityMapper<MTeamEffectRarityDTO, MTeamEffectRarity> {



    default MTeamEffectRarity fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTeamEffectRarity mTeamEffectRarity = new MTeamEffectRarity();
        mTeamEffectRarity.setId(id);
        return mTeamEffectRarity;
    }
}

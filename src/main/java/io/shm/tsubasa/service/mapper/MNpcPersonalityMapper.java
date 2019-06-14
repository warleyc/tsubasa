package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MNpcPersonalityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MNpcPersonality} and its DTO {@link MNpcPersonalityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MNpcPersonalityMapper extends EntityMapper<MNpcPersonalityDTO, MNpcPersonality> {



    default MNpcPersonality fromId(Long id) {
        if (id == null) {
            return null;
        }
        MNpcPersonality mNpcPersonality = new MNpcPersonality();
        mNpcPersonality.setId(id);
        return mNpcPersonality;
    }
}

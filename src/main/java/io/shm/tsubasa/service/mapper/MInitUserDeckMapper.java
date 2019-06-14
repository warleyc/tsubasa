package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MInitUserDeckDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MInitUserDeck} and its DTO {@link MInitUserDeckDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MInitUserDeckMapper extends EntityMapper<MInitUserDeckDTO, MInitUserDeck> {



    default MInitUserDeck fromId(Long id) {
        if (id == null) {
            return null;
        }
        MInitUserDeck mInitUserDeck = new MInitUserDeck();
        mInitUserDeck.setId(id);
        return mInitUserDeck;
    }
}

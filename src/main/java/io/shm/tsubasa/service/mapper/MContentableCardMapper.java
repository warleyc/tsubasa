package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MContentableCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MContentableCard} and its DTO {@link MContentableCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MContentableCardMapper extends EntityMapper<MContentableCardDTO, MContentableCard> {



    default MContentableCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        MContentableCard mContentableCard = new MContentableCard();
        mContentableCard.setId(id);
        return mContentableCard;
    }
}

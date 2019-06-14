package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonEffectiveCard} and its DTO {@link MMarathonEffectiveCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonEffectiveCardMapper extends EntityMapper<MMarathonEffectiveCardDTO, MMarathonEffectiveCard> {



    default MMarathonEffectiveCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonEffectiveCard mMarathonEffectiveCard = new MMarathonEffectiveCard();
        mMarathonEffectiveCard.setId(id);
        return mMarathonEffectiveCard;
    }
}

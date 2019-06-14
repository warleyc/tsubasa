package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGoalEffectiveCard} and its DTO {@link MGoalEffectiveCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGoalEffectiveCardMapper extends EntityMapper<MGoalEffectiveCardDTO, MGoalEffectiveCard> {



    default MGoalEffectiveCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGoalEffectiveCard mGoalEffectiveCard = new MGoalEffectiveCard();
        mGoalEffectiveCard.setId(id);
        return mGoalEffectiveCard;
    }
}

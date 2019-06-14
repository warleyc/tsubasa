package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MSellCardMedalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MSellCardMedal} and its DTO {@link MSellCardMedalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MSellCardMedalMapper extends EntityMapper<MSellCardMedalDTO, MSellCardMedal> {



    default MSellCardMedal fromId(Long id) {
        if (id == null) {
            return null;
        }
        MSellCardMedal mSellCardMedal = new MSellCardMedal();
        mSellCardMedal.setId(id);
        return mSellCardMedal;
    }
}

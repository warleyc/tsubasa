package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTicketQuestTsubasaPointReward} and its DTO {@link MTicketQuestTsubasaPointRewardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTicketQuestTsubasaPointRewardMapper extends EntityMapper<MTicketQuestTsubasaPointRewardDTO, MTicketQuestTsubasaPointReward> {



    default MTicketQuestTsubasaPointReward fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTicketQuestTsubasaPointReward mTicketQuestTsubasaPointReward = new MTicketQuestTsubasaPointReward();
        mTicketQuestTsubasaPointReward.setId(id);
        return mTicketQuestTsubasaPointReward;
    }
}

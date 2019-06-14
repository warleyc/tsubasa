package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestTicketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestTicket} and its DTO {@link MQuestTicketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestTicketMapper extends EntityMapper<MQuestTicketDTO, MQuestTicket> {



    default MQuestTicket fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestTicket mQuestTicket = new MQuestTicket();
        mQuestTicket.setId(id);
        return mQuestTicket;
    }
}

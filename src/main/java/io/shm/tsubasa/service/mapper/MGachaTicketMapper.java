package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaTicketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaTicket} and its DTO {@link MGachaTicketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaTicketMapper extends EntityMapper<MGachaTicketDTO, MGachaTicket> {



    default MGachaTicket fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaTicket mGachaTicket = new MGachaTicket();
        mGachaTicket.setId(id);
        return mGachaTicket;
    }
}

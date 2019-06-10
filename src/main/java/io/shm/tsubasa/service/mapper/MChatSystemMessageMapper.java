package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MChatSystemMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MChatSystemMessage} and its DTO {@link MChatSystemMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MChatSystemMessageMapper extends EntityMapper<MChatSystemMessageDTO, MChatSystemMessage> {



    default MChatSystemMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MChatSystemMessage mChatSystemMessage = new MChatSystemMessage();
        mChatSystemMessage.setId(id);
        return mChatSystemMessage;
    }
}

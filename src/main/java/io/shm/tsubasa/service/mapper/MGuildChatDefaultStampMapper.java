package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildChatDefaultStamp} and its DTO {@link MGuildChatDefaultStampDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuildChatDefaultStampMapper extends EntityMapper<MGuildChatDefaultStampDTO, MGuildChatDefaultStamp> {



    default MGuildChatDefaultStamp fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildChatDefaultStamp mGuildChatDefaultStamp = new MGuildChatDefaultStamp();
        mGuildChatDefaultStamp.setId(id);
        return mGuildChatDefaultStamp;
    }
}

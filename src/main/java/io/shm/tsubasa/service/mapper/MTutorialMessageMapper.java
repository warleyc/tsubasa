package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTutorialMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTutorialMessage} and its DTO {@link MTutorialMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTutorialMessageMapper extends EntityMapper<MTutorialMessageDTO, MTutorialMessage> {



    default MTutorialMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTutorialMessage mTutorialMessage = new MTutorialMessage();
        mTutorialMessage.setId(id);
        return mTutorialMessage;
    }
}

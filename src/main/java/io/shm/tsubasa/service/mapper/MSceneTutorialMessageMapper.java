package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MSceneTutorialMessage} and its DTO {@link MSceneTutorialMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MSceneTutorialMessageMapper extends EntityMapper<MSceneTutorialMessageDTO, MSceneTutorialMessage> {



    default MSceneTutorialMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MSceneTutorialMessage mSceneTutorialMessage = new MSceneTutorialMessage();
        mSceneTutorialMessage.setId(id);
        return mSceneTutorialMessage;
    }
}

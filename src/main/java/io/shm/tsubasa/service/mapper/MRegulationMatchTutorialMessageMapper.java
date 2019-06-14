package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MRegulationMatchTutorialMessage} and its DTO {@link MRegulationMatchTutorialMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MRegulationMatchTutorialMessageMapper extends EntityMapper<MRegulationMatchTutorialMessageDTO, MRegulationMatchTutorialMessage> {



    default MRegulationMatchTutorialMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MRegulationMatchTutorialMessage mRegulationMatchTutorialMessage = new MRegulationMatchTutorialMessage();
        mRegulationMatchTutorialMessage.setId(id);
        return mRegulationMatchTutorialMessage;
    }
}

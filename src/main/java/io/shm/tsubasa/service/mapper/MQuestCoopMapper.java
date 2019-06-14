package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestCoopDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestCoop} and its DTO {@link MQuestCoopDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestCoopMapper extends EntityMapper<MQuestCoopDTO, MQuestCoop> {



    default MQuestCoop fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestCoop mQuestCoop = new MQuestCoop();
        mQuestCoop.setId(id);
        return mQuestCoop;
    }
}

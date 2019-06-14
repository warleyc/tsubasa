package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MQuestWorld} and its DTO {@link MQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MQuestWorldMapper extends EntityMapper<MQuestWorldDTO, MQuestWorld> {


    @Mapping(target = "mQuestStages", ignore = true)
    MQuestWorld toEntity(MQuestWorldDTO mQuestWorldDTO);

    default MQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MQuestWorld mQuestWorld = new MQuestWorld();
        mQuestWorld.setId(id);
        return mQuestWorld;
    }
}

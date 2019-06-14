package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuerillaQuestWorld} and its DTO {@link MGuerillaQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuerillaQuestWorldMapper extends EntityMapper<MGuerillaQuestWorldDTO, MGuerillaQuestWorld> {


    @Mapping(target = "mGuerillaQuestStages", ignore = true)
    MGuerillaQuestWorld toEntity(MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO);

    default MGuerillaQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuerillaQuestWorld mGuerillaQuestWorld = new MGuerillaQuestWorld();
        mGuerillaQuestWorld.setId(id);
        return mGuerillaQuestWorld;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAdventQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAdventQuestWorld} and its DTO {@link MAdventQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MAdventQuestWorldMapper extends EntityMapper<MAdventQuestWorldDTO, MAdventQuestWorld> {


    @Mapping(target = "mAdventQuestStages", ignore = true)
    MAdventQuestWorld toEntity(MAdventQuestWorldDTO mAdventQuestWorldDTO);

    default MAdventQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAdventQuestWorld mAdventQuestWorld = new MAdventQuestWorld();
        mAdventQuestWorld.setId(id);
        return mAdventQuestWorld;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MWeeklyQuestWorld} and its DTO {@link MWeeklyQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MWeeklyQuestWorldMapper extends EntityMapper<MWeeklyQuestWorldDTO, MWeeklyQuestWorld> {


    @Mapping(target = "mWeeklyQuestStages", ignore = true)
    MWeeklyQuestWorld toEntity(MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO);

    default MWeeklyQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MWeeklyQuestWorld mWeeklyQuestWorld = new MWeeklyQuestWorld();
        mWeeklyQuestWorld.setId(id);
        return mWeeklyQuestWorld;
    }
}

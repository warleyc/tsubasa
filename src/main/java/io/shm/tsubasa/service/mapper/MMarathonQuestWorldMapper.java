package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonQuestWorld} and its DTO {@link MMarathonQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonQuestWorldMapper extends EntityMapper<MMarathonQuestWorldDTO, MMarathonQuestWorld> {


    @Mapping(target = "mMarathonQuestStages", ignore = true)
    MMarathonQuestWorld toEntity(MMarathonQuestWorldDTO mMarathonQuestWorldDTO);

    default MMarathonQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonQuestWorld mMarathonQuestWorld = new MMarathonQuestWorld();
        mMarathonQuestWorld.setId(id);
        return mMarathonQuestWorld;
    }
}

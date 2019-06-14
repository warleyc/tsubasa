package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTimeattackQuestWorld} and its DTO {@link MTimeattackQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTimeattackQuestWorldMapper extends EntityMapper<MTimeattackQuestWorldDTO, MTimeattackQuestWorld> {


    @Mapping(target = "mTimeattackQuestStages", ignore = true)
    MTimeattackQuestWorld toEntity(MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO);

    default MTimeattackQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTimeattackQuestWorld mTimeattackQuestWorld = new MTimeattackQuestWorld();
        mTimeattackQuestWorld.setId(id);
        return mTimeattackQuestWorld;
    }
}

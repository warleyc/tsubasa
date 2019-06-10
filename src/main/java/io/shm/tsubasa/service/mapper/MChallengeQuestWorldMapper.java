package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MChallengeQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MChallengeQuestWorld} and its DTO {@link MChallengeQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MChallengeQuestWorldMapper extends EntityMapper<MChallengeQuestWorldDTO, MChallengeQuestWorld> {


    @Mapping(target = "mChallengeQuestStages", ignore = true)
    MChallengeQuestWorld toEntity(MChallengeQuestWorldDTO mChallengeQuestWorldDTO);

    default MChallengeQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MChallengeQuestWorld mChallengeQuestWorld = new MChallengeQuestWorld();
        mChallengeQuestWorld.setId(id);
        return mChallengeQuestWorld;
    }
}

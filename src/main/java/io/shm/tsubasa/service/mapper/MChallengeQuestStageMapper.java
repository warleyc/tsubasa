package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MChallengeQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MChallengeQuestStage} and its DTO {@link MChallengeQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MChallengeQuestWorldMapper.class})
public interface MChallengeQuestStageMapper extends EntityMapper<MChallengeQuestStageDTO, MChallengeQuestStage> {

    @Mapping(source = "mchallengequestworld.id", target = "mchallengequestworldId")
    MChallengeQuestStageDTO toDto(MChallengeQuestStage mChallengeQuestStage);

    @Mapping(source = "mchallengequestworldId", target = "mchallengequestworld")
    MChallengeQuestStage toEntity(MChallengeQuestStageDTO mChallengeQuestStageDTO);

    default MChallengeQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MChallengeQuestStage mChallengeQuestStage = new MChallengeQuestStage();
        mChallengeQuestStage.setId(id);
        return mChallengeQuestStage;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTicketQuestStageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTicketQuestStage} and its DTO {@link MTicketQuestStageDTO}.
 */
@Mapper(componentModel = "spring", uses = {MTicketQuestWorldMapper.class})
public interface MTicketQuestStageMapper extends EntityMapper<MTicketQuestStageDTO, MTicketQuestStage> {

    @Mapping(source = "mticketquestworld.id", target = "mticketquestworldId")
    MTicketQuestStageDTO toDto(MTicketQuestStage mTicketQuestStage);

    @Mapping(source = "mticketquestworldId", target = "mticketquestworld")
    MTicketQuestStage toEntity(MTicketQuestStageDTO mTicketQuestStageDTO);

    default MTicketQuestStage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTicketQuestStage mTicketQuestStage = new MTicketQuestStage();
        mTicketQuestStage.setId(id);
        return mTicketQuestStage;
    }
}

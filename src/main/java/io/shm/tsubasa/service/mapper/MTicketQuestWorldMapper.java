package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTicketQuestWorldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTicketQuestWorld} and its DTO {@link MTicketQuestWorldDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTicketQuestWorldMapper extends EntityMapper<MTicketQuestWorldDTO, MTicketQuestWorld> {


    @Mapping(target = "mTicketQuestStages", ignore = true)
    MTicketQuestWorld toEntity(MTicketQuestWorldDTO mTicketQuestWorldDTO);

    default MTicketQuestWorld fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTicketQuestWorld mTicketQuestWorld = new MTicketQuestWorld();
        mTicketQuestWorld.setId(id);
        return mTicketQuestWorld;
    }
}

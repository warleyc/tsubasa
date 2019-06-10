package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MEncountersCutDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MEncountersCut} and its DTO {@link MEncountersCutDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MEncountersCutMapper extends EntityMapper<MEncountersCutDTO, MEncountersCut> {



    default MEncountersCut fromId(Long id) {
        if (id == null) {
            return null;
        }
        MEncountersCut mEncountersCut = new MEncountersCut();
        mEncountersCut.setId(id);
        return mEncountersCut;
    }
}

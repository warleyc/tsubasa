package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MEncountersBonusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MEncountersBonus} and its DTO {@link MEncountersBonusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MEncountersBonusMapper extends EntityMapper<MEncountersBonusDTO, MEncountersBonus> {



    default MEncountersBonus fromId(Long id) {
        if (id == null) {
            return null;
        }
        MEncountersBonus mEncountersBonus = new MEncountersBonus();
        mEncountersBonus.setId(id);
        return mEncountersBonus;
    }
}

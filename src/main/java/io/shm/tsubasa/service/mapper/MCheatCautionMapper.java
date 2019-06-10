package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCheatCautionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCheatCaution} and its DTO {@link MCheatCautionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCheatCautionMapper extends EntityMapper<MCheatCautionDTO, MCheatCaution> {



    default MCheatCaution fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCheatCaution mCheatCaution = new MCheatCaution();
        mCheatCaution.setId(id);
        return mCheatCaution;
    }
}

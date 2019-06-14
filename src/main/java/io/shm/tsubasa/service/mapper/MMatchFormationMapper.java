package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMatchFormationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMatchFormation} and its DTO {@link MMatchFormationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMatchFormationMapper extends EntityMapper<MMatchFormationDTO, MMatchFormation> {



    default MMatchFormation fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMatchFormation mMatchFormation = new MMatchFormation();
        mMatchFormation.setId(id);
        return mMatchFormation;
    }
}

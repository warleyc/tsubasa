package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MConstantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MConstant} and its DTO {@link MConstantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MConstantMapper extends EntityMapper<MConstantDTO, MConstant> {



    default MConstant fromId(Long id) {
        if (id == null) {
            return null;
        }
        MConstant mConstant = new MConstant();
        mConstant.setId(id);
        return mConstant;
    }
}

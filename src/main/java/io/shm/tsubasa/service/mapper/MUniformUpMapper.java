package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MUniformUpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MUniformUp} and its DTO {@link MUniformUpDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MUniformUpMapper extends EntityMapper<MUniformUpDTO, MUniformUp> {



    default MUniformUp fromId(Long id) {
        if (id == null) {
            return null;
        }
        MUniformUp mUniformUp = new MUniformUp();
        mUniformUp.setId(id);
        return mUniformUp;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MModelUniformUpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MModelUniformUp} and its DTO {@link MModelUniformUpDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MModelUniformUpMapper extends EntityMapper<MModelUniformUpDTO, MModelUniformUp> {



    default MModelUniformUp fromId(Long id) {
        if (id == null) {
            return null;
        }
        MModelUniformUp mModelUniformUp = new MModelUniformUp();
        mModelUniformUp.setId(id);
        return mModelUniformUp;
    }
}

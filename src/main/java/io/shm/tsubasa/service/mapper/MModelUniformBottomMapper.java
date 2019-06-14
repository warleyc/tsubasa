package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MModelUniformBottomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MModelUniformBottom} and its DTO {@link MModelUniformBottomDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MModelUniformBottomMapper extends EntityMapper<MModelUniformBottomDTO, MModelUniformBottom> {



    default MModelUniformBottom fromId(Long id) {
        if (id == null) {
            return null;
        }
        MModelUniformBottom mModelUniformBottom = new MModelUniformBottom();
        mModelUniformBottom.setId(id);
        return mModelUniformBottom;
    }
}

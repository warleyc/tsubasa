package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MUniformBottomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MUniformBottom} and its DTO {@link MUniformBottomDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MUniformBottomMapper extends EntityMapper<MUniformBottomDTO, MUniformBottom> {



    default MUniformBottom fromId(Long id) {
        if (id == null) {
            return null;
        }
        MUniformBottom mUniformBottom = new MUniformBottom();
        mUniformBottom.setId(id);
        return mUniformBottom;
    }
}

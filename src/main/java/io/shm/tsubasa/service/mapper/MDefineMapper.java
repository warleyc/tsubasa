package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDefineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDefine} and its DTO {@link MDefineDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDefineMapper extends EntityMapper<MDefineDTO, MDefine> {



    default MDefine fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDefine mDefine = new MDefine();
        mDefine.setId(id);
        return mDefine;
    }
}

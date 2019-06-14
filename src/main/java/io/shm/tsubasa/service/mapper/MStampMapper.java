package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MStampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MStamp} and its DTO {@link MStampDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MStampMapper extends EntityMapper<MStampDTO, MStamp> {



    default MStamp fromId(Long id) {
        if (id == null) {
            return null;
        }
        MStamp mStamp = new MStamp();
        mStamp.setId(id);
        return mStamp;
    }
}

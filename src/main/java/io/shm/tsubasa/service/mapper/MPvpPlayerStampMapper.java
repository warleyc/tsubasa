package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpPlayerStampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpPlayerStamp} and its DTO {@link MPvpPlayerStampDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPvpPlayerStampMapper extends EntityMapper<MPvpPlayerStampDTO, MPvpPlayerStamp> {



    default MPvpPlayerStamp fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpPlayerStamp mPvpPlayerStamp = new MPvpPlayerStamp();
        mPvpPlayerStamp.setId(id);
        return mPvpPlayerStamp;
    }
}

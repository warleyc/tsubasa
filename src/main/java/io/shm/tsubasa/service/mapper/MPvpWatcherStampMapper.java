package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpWatcherStampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpWatcherStamp} and its DTO {@link MPvpWatcherStampDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPvpWatcherStampMapper extends EntityMapper<MPvpWatcherStampDTO, MPvpWatcherStamp> {



    default MPvpWatcherStamp fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpWatcherStamp mPvpWatcherStamp = new MPvpWatcherStamp();
        mPvpWatcherStamp.setId(id);
        return mPvpWatcherStamp;
    }
}

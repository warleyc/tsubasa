package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCoopRoomStampDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCoopRoomStamp} and its DTO {@link MCoopRoomStampDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCoopRoomStampMapper extends EntityMapper<MCoopRoomStampDTO, MCoopRoomStamp> {



    default MCoopRoomStamp fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCoopRoomStamp mCoopRoomStamp = new MCoopRoomStamp();
        mCoopRoomStamp.setId(id);
        return mCoopRoomStamp;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MBadgeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MBadge} and its DTO {@link MBadgeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MBadgeMapper extends EntityMapper<MBadgeDTO, MBadge> {



    default MBadge fromId(Long id) {
        if (id == null) {
            return null;
        }
        MBadge mBadge = new MBadge();
        mBadge.setId(id);
        return mBadge;
    }
}

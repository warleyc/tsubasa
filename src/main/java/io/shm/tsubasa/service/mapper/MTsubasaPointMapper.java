package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTsubasaPointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTsubasaPoint} and its DTO {@link MTsubasaPointDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTsubasaPointMapper extends EntityMapper<MTsubasaPointDTO, MTsubasaPoint> {



    default MTsubasaPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTsubasaPoint mTsubasaPoint = new MTsubasaPoint();
        mTsubasaPoint.setId(id);
        return mTsubasaPoint;
    }
}

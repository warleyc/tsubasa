package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MFullPowerPointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MFullPowerPoint} and its DTO {@link MFullPowerPointDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MFullPowerPointMapper extends EntityMapper<MFullPowerPointDTO, MFullPowerPoint> {



    default MFullPowerPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        MFullPowerPoint mFullPowerPoint = new MFullPowerPoint();
        mFullPowerPoint.setId(id);
        return mFullPowerPoint;
    }
}

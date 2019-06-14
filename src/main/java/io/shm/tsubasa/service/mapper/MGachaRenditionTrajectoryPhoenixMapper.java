package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionTrajectoryPhoenix} and its DTO {@link MGachaRenditionTrajectoryPhoenixDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionTrajectoryPhoenixMapper extends EntityMapper<MGachaRenditionTrajectoryPhoenixDTO, MGachaRenditionTrajectoryPhoenix> {



    default MGachaRenditionTrajectoryPhoenix fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionTrajectoryPhoenix mGachaRenditionTrajectoryPhoenix = new MGachaRenditionTrajectoryPhoenix();
        mGachaRenditionTrajectoryPhoenix.setId(id);
        return mGachaRenditionTrajectoryPhoenix;
    }
}

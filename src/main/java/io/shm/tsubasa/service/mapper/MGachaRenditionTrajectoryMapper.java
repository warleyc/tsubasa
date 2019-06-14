package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionTrajectory} and its DTO {@link MGachaRenditionTrajectoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionTrajectoryMapper extends EntityMapper<MGachaRenditionTrajectoryDTO, MGachaRenditionTrajectory> {



    default MGachaRenditionTrajectory fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionTrajectory mGachaRenditionTrajectory = new MGachaRenditionTrajectory();
        mGachaRenditionTrajectory.setId(id);
        return mGachaRenditionTrajectory;
    }
}

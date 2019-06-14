package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionTrajectoryCutIn} and its DTO {@link MGachaRenditionTrajectoryCutInDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionTrajectoryCutInMapper extends EntityMapper<MGachaRenditionTrajectoryCutInDTO, MGachaRenditionTrajectoryCutIn> {



    default MGachaRenditionTrajectoryCutIn fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionTrajectoryCutIn mGachaRenditionTrajectoryCutIn = new MGachaRenditionTrajectoryCutIn();
        mGachaRenditionTrajectoryCutIn.setId(id);
        return mGachaRenditionTrajectoryCutIn;
    }
}

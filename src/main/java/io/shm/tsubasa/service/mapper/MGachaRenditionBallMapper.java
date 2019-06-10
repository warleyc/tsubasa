package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionBallDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionBall} and its DTO {@link MGachaRenditionBallDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionBallMapper extends EntityMapper<MGachaRenditionBallDTO, MGachaRenditionBall> {



    default MGachaRenditionBall fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionBall mGachaRenditionBall = new MGachaRenditionBall();
        mGachaRenditionBall.setId(id);
        return mGachaRenditionBall;
    }
}

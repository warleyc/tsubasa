package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRendition} and its DTO {@link MGachaRenditionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionMapper extends EntityMapper<MGachaRenditionDTO, MGachaRendition> {



    default MGachaRendition fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRendition mGachaRendition = new MGachaRendition();
        mGachaRendition.setId(id);
        return mGachaRendition;
    }
}

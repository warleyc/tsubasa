package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionKicker} and its DTO {@link MGachaRenditionKickerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionKickerMapper extends EntityMapper<MGachaRenditionKickerDTO, MGachaRenditionKicker> {



    default MGachaRenditionKicker fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionKicker mGachaRenditionKicker = new MGachaRenditionKicker();
        mGachaRenditionKicker.setId(id);
        return mGachaRenditionKicker;
    }
}

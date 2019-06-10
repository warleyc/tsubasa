package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionAfterInputCutIn} and its DTO {@link MGachaRenditionAfterInputCutInDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionAfterInputCutInMapper extends EntityMapper<MGachaRenditionAfterInputCutInDTO, MGachaRenditionAfterInputCutIn> {



    default MGachaRenditionAfterInputCutIn fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionAfterInputCutIn mGachaRenditionAfterInputCutIn = new MGachaRenditionAfterInputCutIn();
        mGachaRenditionAfterInputCutIn.setId(id);
        return mGachaRenditionAfterInputCutIn;
    }
}

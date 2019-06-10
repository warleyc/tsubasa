package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionAfterInputCutInTextColor} and its DTO {@link MGachaRenditionAfterInputCutInTextColorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionAfterInputCutInTextColorMapper extends EntityMapper<MGachaRenditionAfterInputCutInTextColorDTO, MGachaRenditionAfterInputCutInTextColor> {



    default MGachaRenditionAfterInputCutInTextColor fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionAfterInputCutInTextColor mGachaRenditionAfterInputCutInTextColor = new MGachaRenditionAfterInputCutInTextColor();
        mGachaRenditionAfterInputCutInTextColor.setId(id);
        return mGachaRenditionAfterInputCutInTextColor;
    }
}

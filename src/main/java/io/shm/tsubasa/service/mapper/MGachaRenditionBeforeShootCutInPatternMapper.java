package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionBeforeShootCutInPattern} and its DTO {@link MGachaRenditionBeforeShootCutInPatternDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionBeforeShootCutInPatternMapper extends EntityMapper<MGachaRenditionBeforeShootCutInPatternDTO, MGachaRenditionBeforeShootCutInPattern> {



    default MGachaRenditionBeforeShootCutInPattern fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionBeforeShootCutInPattern mGachaRenditionBeforeShootCutInPattern = new MGachaRenditionBeforeShootCutInPattern();
        mGachaRenditionBeforeShootCutInPattern.setId(id);
        return mGachaRenditionBeforeShootCutInPattern;
    }
}

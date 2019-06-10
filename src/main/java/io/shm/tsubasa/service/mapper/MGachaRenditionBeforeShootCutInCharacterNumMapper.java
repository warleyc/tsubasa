package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionBeforeShootCutInCharacterNum} and its DTO {@link MGachaRenditionBeforeShootCutInCharacterNumDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionBeforeShootCutInCharacterNumMapper extends EntityMapper<MGachaRenditionBeforeShootCutInCharacterNumDTO, MGachaRenditionBeforeShootCutInCharacterNum> {



    default MGachaRenditionBeforeShootCutInCharacterNum fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionBeforeShootCutInCharacterNum mGachaRenditionBeforeShootCutInCharacterNum = new MGachaRenditionBeforeShootCutInCharacterNum();
        mGachaRenditionBeforeShootCutInCharacterNum.setId(id);
        return mGachaRenditionBeforeShootCutInCharacterNum;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionExtraCutin} and its DTO {@link MGachaRenditionExtraCutinDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionExtraCutinMapper extends EntityMapper<MGachaRenditionExtraCutinDTO, MGachaRenditionExtraCutin> {



    default MGachaRenditionExtraCutin fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionExtraCutin mGachaRenditionExtraCutin = new MGachaRenditionExtraCutin();
        mGachaRenditionExtraCutin.setId(id);
        return mGachaRenditionExtraCutin;
    }
}

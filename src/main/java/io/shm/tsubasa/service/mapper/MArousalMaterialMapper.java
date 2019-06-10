package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MArousalMaterialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MArousalMaterial} and its DTO {@link MArousalMaterialDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MArousalMaterialMapper extends EntityMapper<MArousalMaterialDTO, MArousalMaterial> {



    default MArousalMaterial fromId(Long id) {
        if (id == null) {
            return null;
        }
        MArousalMaterial mArousalMaterial = new MArousalMaterial();
        mArousalMaterial.setId(id);
        return mArousalMaterial;
    }
}

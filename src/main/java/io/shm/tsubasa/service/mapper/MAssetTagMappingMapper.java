package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAssetTagMappingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAssetTagMapping} and its DTO {@link MAssetTagMappingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MAssetTagMappingMapper extends EntityMapper<MAssetTagMappingDTO, MAssetTagMapping> {



    default MAssetTagMapping fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAssetTagMapping mAssetTagMapping = new MAssetTagMapping();
        mAssetTagMapping.setId(id);
        return mAssetTagMapping;
    }
}

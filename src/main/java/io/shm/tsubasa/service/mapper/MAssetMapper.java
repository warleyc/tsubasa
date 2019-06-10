package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAssetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAsset} and its DTO {@link MAssetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MAssetMapper extends EntityMapper<MAssetDTO, MAsset> {



    default MAsset fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAsset mAsset = new MAsset();
        mAsset.setId(id);
        return mAsset;
    }
}

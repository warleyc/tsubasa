package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCardPlayableAssets} and its DTO {@link MCardPlayableAssetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCardPlayableAssetsMapper extends EntityMapper<MCardPlayableAssetsDTO, MCardPlayableAssets> {



    default MCardPlayableAssets fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCardPlayableAssets mCardPlayableAssets = new MCardPlayableAssets();
        mCardPlayableAssets.setId(id);
        return mCardPlayableAssets;
    }
}

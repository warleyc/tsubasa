package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCardIllustAssetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCardIllustAssets} and its DTO {@link MCardIllustAssetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCardIllustAssetsMapper extends EntityMapper<MCardIllustAssetsDTO, MCardIllustAssets> {



    default MCardIllustAssets fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCardIllustAssets mCardIllustAssets = new MCardIllustAssets();
        mCardIllustAssets.setId(id);
        return mCardIllustAssets;
    }
}

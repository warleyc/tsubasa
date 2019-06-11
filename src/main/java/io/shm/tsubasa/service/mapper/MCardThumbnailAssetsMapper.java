package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCardThumbnailAssets} and its DTO {@link MCardThumbnailAssetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCardThumbnailAssetsMapper extends EntityMapper<MCardThumbnailAssetsDTO, MCardThumbnailAssets> {


    @Mapping(target = "mCardPowerupActionSkills", ignore = true)
    @Mapping(target = "removeMCardPowerupActionSkill", ignore = true)
    @Mapping(target = "mTrainingCards", ignore = true)
    @Mapping(target = "removeMTrainingCard", ignore = true)
    MCardThumbnailAssets toEntity(MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO);

    default MCardThumbnailAssets fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCardThumbnailAssets mCardThumbnailAssets = new MCardThumbnailAssets();
        mCardThumbnailAssets.setId(id);
        return mCardThumbnailAssets;
    }
}

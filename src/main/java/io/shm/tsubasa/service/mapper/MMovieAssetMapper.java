package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMovieAssetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMovieAsset} and its DTO {@link MMovieAssetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMovieAssetMapper extends EntityMapper<MMovieAssetDTO, MMovieAsset> {



    default MMovieAsset fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMovieAsset mMovieAsset = new MMovieAsset();
        mMovieAsset.setId(id);
        return mMovieAsset;
    }
}

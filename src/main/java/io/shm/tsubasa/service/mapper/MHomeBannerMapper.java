package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MHomeBannerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MHomeBanner} and its DTO {@link MHomeBannerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MHomeBannerMapper extends EntityMapper<MHomeBannerDTO, MHomeBanner> {



    default MHomeBanner fromId(Long id) {
        if (id == null) {
            return null;
        }
        MHomeBanner mHomeBanner = new MHomeBanner();
        mHomeBanner.setId(id);
        return mHomeBanner;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCommonBannerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCommonBanner} and its DTO {@link MCommonBannerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCommonBannerMapper extends EntityMapper<MCommonBannerDTO, MCommonBanner> {



    default MCommonBanner fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCommonBanner mCommonBanner = new MCommonBanner();
        mCommonBanner.setId(id);
        return mCommonBanner;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildTopBannerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildTopBanner} and its DTO {@link MGuildTopBannerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuildTopBannerMapper extends EntityMapper<MGuildTopBannerDTO, MGuildTopBanner> {



    default MGuildTopBanner fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildTopBanner mGuildTopBanner = new MGuildTopBanner();
        mGuildTopBanner.setId(id);
        return mGuildTopBanner;
    }
}

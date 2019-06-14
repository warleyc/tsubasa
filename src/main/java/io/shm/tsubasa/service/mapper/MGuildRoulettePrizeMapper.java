package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildRoulettePrize} and its DTO {@link MGuildRoulettePrizeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuildRoulettePrizeMapper extends EntityMapper<MGuildRoulettePrizeDTO, MGuildRoulettePrize> {



    default MGuildRoulettePrize fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildRoulettePrize mGuildRoulettePrize = new MGuildRoulettePrize();
        mGuildRoulettePrize.setId(id);
        return mGuildRoulettePrize;
    }
}

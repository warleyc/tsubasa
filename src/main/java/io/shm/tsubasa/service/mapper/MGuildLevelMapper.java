package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildLevel} and its DTO {@link MGuildLevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuildLevelMapper extends EntityMapper<MGuildLevelDTO, MGuildLevel> {



    default MGuildLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildLevel mGuildLevel = new MGuildLevel();
        mGuildLevel.setId(id);
        return mGuildLevel;
    }
}

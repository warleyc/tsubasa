package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLeagueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLeague} and its DTO {@link MLeagueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLeagueMapper extends EntityMapper<MLeagueDTO, MLeague> {



    default MLeague fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLeague mLeague = new MLeague();
        mLeague.setId(id);
        return mLeague;
    }
}

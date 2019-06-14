package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MSituationAnnounceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MSituationAnnounce} and its DTO {@link MSituationAnnounceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MSituationAnnounceMapper extends EntityMapper<MSituationAnnounceDTO, MSituationAnnounce> {



    default MSituationAnnounce fromId(Long id) {
        if (id == null) {
            return null;
        }
        MSituationAnnounce mSituationAnnounce = new MSituationAnnounce();
        mSituationAnnounce.setId(id);
        return mSituationAnnounce;
    }
}

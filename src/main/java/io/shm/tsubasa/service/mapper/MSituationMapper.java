package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MSituationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MSituation} and its DTO {@link MSituationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MSituationMapper extends EntityMapper<MSituationDTO, MSituation> {



    default MSituation fromId(Long id) {
        if (id == null) {
            return null;
        }
        MSituation mSituation = new MSituation();
        mSituation.setId(id);
        return mSituation;
    }
}

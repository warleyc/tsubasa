package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MRivalEncountCutinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MRivalEncountCutin} and its DTO {@link MRivalEncountCutinDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MRivalEncountCutinMapper extends EntityMapper<MRivalEncountCutinDTO, MRivalEncountCutin> {



    default MRivalEncountCutin fromId(Long id) {
        if (id == null) {
            return null;
        }
        MRivalEncountCutin mRivalEncountCutin = new MRivalEncountCutin();
        mRivalEncountCutin.setId(id);
        return mRivalEncountCutin;
    }
}

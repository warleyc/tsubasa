package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPenaltyKickCutDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPenaltyKickCut} and its DTO {@link MPenaltyKickCutDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPenaltyKickCutMapper extends EntityMapper<MPenaltyKickCutDTO, MPenaltyKickCut> {



    default MPenaltyKickCut fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPenaltyKickCut mPenaltyKickCut = new MPenaltyKickCut();
        mPenaltyKickCut.setId(id);
        return mPenaltyKickCut;
    }
}

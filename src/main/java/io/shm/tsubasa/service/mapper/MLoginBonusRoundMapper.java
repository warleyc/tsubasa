package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLoginBonusRoundDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLoginBonusRound} and its DTO {@link MLoginBonusRoundDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLoginBonusRoundMapper extends EntityMapper<MLoginBonusRoundDTO, MLoginBonusRound> {



    default MLoginBonusRound fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLoginBonusRound mLoginBonusRound = new MLoginBonusRound();
        mLoginBonusRound.setId(id);
        return mLoginBonusRound;
    }
}

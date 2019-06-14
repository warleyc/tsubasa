package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLoginBonusSerifDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLoginBonusSerif} and its DTO {@link MLoginBonusSerifDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLoginBonusSerifMapper extends EntityMapper<MLoginBonusSerifDTO, MLoginBonusSerif> {



    default MLoginBonusSerif fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLoginBonusSerif mLoginBonusSerif = new MLoginBonusSerif();
        mLoginBonusSerif.setId(id);
        return mLoginBonusSerif;
    }
}

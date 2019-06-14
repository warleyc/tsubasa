package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLoginBonusIncentive} and its DTO {@link MLoginBonusIncentiveDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLoginBonusIncentiveMapper extends EntityMapper<MLoginBonusIncentiveDTO, MLoginBonusIncentive> {



    default MLoginBonusIncentive fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLoginBonusIncentive mLoginBonusIncentive = new MLoginBonusIncentive();
        mLoginBonusIncentive.setId(id);
        return mLoginBonusIncentive;
    }
}

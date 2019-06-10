package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionTradeSign} and its DTO {@link MGachaRenditionTradeSignDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionTradeSignMapper extends EntityMapper<MGachaRenditionTradeSignDTO, MGachaRenditionTradeSign> {



    default MGachaRenditionTradeSign fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionTradeSign mGachaRenditionTradeSign = new MGachaRenditionTradeSign();
        mGachaRenditionTradeSign.setId(id);
        return mGachaRenditionTradeSign;
    }
}

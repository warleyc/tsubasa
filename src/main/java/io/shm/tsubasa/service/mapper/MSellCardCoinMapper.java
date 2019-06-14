package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MSellCardCoinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MSellCardCoin} and its DTO {@link MSellCardCoinDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MSellCardCoinMapper extends EntityMapper<MSellCardCoinDTO, MSellCardCoin> {



    default MSellCardCoin fromId(Long id) {
        if (id == null) {
            return null;
        }
        MSellCardCoin mSellCardCoin = new MSellCardCoin();
        mSellCardCoin.setId(id);
        return mSellCardCoin;
    }
}

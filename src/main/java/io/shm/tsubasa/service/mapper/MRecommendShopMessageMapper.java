package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MRecommendShopMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MRecommendShopMessage} and its DTO {@link MRecommendShopMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MRecommendShopMessageMapper extends EntityMapper<MRecommendShopMessageDTO, MRecommendShopMessage> {



    default MRecommendShopMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        MRecommendShopMessage mRecommendShopMessage = new MRecommendShopMessage();
        mRecommendShopMessage.setId(id);
        return mRecommendShopMessage;
    }
}

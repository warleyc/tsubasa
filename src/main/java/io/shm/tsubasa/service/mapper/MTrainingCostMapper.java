package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTrainingCostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTrainingCost} and its DTO {@link MTrainingCostDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTrainingCostMapper extends EntityMapper<MTrainingCostDTO, MTrainingCost> {



    default MTrainingCost fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTrainingCost mTrainingCost = new MTrainingCost();
        mTrainingCost.setId(id);
        return mTrainingCost;
    }
}

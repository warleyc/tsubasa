package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAreaActionWeightDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAreaActionWeight} and its DTO {@link MAreaActionWeightDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MAreaActionWeightMapper extends EntityMapper<MAreaActionWeightDTO, MAreaActionWeight> {



    default MAreaActionWeight fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAreaActionWeight mAreaActionWeight = new MAreaActionWeight();
        mAreaActionWeight.setId(id);
        return mAreaActionWeight;
    }
}

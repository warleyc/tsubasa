package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLuckDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLuck} and its DTO {@link MLuckDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MLuckMapper extends EntityMapper<MLuckDTO, MLuck> {



    default MLuck fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLuck mLuck = new MLuck();
        mLuck.setId(id);
        return mLuck;
    }
}

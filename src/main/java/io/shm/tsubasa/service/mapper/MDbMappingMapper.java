package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDbMappingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDbMapping} and its DTO {@link MDbMappingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDbMappingMapper extends EntityMapper<MDbMappingDTO, MDbMapping> {



    default MDbMapping fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDbMapping mDbMapping = new MDbMapping();
        mDbMapping.setId(id);
        return mDbMapping;
    }
}

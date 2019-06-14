package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMasterVersionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMasterVersion} and its DTO {@link MMasterVersionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMasterVersionMapper extends EntityMapper<MMasterVersionDTO, MMasterVersion> {



    default MMasterVersion fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMasterVersion mMasterVersion = new MMasterVersion();
        mMasterVersion.setId(id);
        return mMasterVersion;
    }
}

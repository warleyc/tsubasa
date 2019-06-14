package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MModelUniformUpResource} and its DTO {@link MModelUniformUpResourceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MModelUniformUpResourceMapper extends EntityMapper<MModelUniformUpResourceDTO, MModelUniformUpResource> {



    default MModelUniformUpResource fromId(Long id) {
        if (id == null) {
            return null;
        }
        MModelUniformUpResource mModelUniformUpResource = new MModelUniformUpResource();
        mModelUniformUpResource.setId(id);
        return mModelUniformUpResource;
    }
}

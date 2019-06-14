package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MModelUniformBottomResource} and its DTO {@link MModelUniformBottomResourceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MModelUniformBottomResourceMapper extends EntityMapper<MModelUniformBottomResourceDTO, MModelUniformBottomResource> {



    default MModelUniformBottomResource fromId(Long id) {
        if (id == null) {
            return null;
        }
        MModelUniformBottomResource mModelUniformBottomResource = new MModelUniformBottomResource();
        mModelUniformBottomResource.setId(id);
        return mModelUniformBottomResource;
    }
}

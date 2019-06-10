package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAction} and its DTO {@link MActionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MActionMapper extends EntityMapper<MActionDTO, MAction> {


    @Mapping(target = "mTargetActionGroups", ignore = true)
    MAction toEntity(MActionDTO mActionDTO);

    default MAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAction mAction = new MAction();
        mAction.setId(id);
        return mAction;
    }
}

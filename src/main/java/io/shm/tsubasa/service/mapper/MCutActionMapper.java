package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCutActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCutAction} and its DTO {@link MCutActionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCutActionMapper extends EntityMapper<MCutActionDTO, MCutAction> {



    default MCutAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCutAction mCutAction = new MCutAction();
        mCutAction.setId(id);
        return mCutAction;
    }
}

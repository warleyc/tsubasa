package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MKeeperCutActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MKeeperCutAction} and its DTO {@link MKeeperCutActionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MKeeperCutActionMapper extends EntityMapper<MKeeperCutActionDTO, MKeeperCutAction> {



    default MKeeperCutAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        MKeeperCutAction mKeeperCutAction = new MKeeperCutAction();
        mKeeperCutAction.setId(id);
        return mKeeperCutAction;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMatchEnvironmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMatchEnvironment} and its DTO {@link MMatchEnvironmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMatchEnvironmentMapper extends EntityMapper<MMatchEnvironmentDTO, MMatchEnvironment> {



    default MMatchEnvironment fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMatchEnvironment mMatchEnvironment = new MMatchEnvironment();
        mMatchEnvironment.setId(id);
        return mMatchEnvironment;
    }
}

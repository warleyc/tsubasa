package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCutShootOrbitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCutShootOrbit} and its DTO {@link MCutShootOrbitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCutShootOrbitMapper extends EntityMapper<MCutShootOrbitDTO, MCutShootOrbit> {



    default MCutShootOrbit fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCutShootOrbit mCutShootOrbit = new MCutShootOrbit();
        mCutShootOrbit.setId(id);
        return mCutShootOrbit;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MShootDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MShoot} and its DTO {@link MShootDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MShootMapper extends EntityMapper<MShootDTO, MShoot> {



    default MShoot fromId(Long id) {
        if (id == null) {
            return null;
        }
        MShoot mShoot = new MShoot();
        mShoot.setId(id);
        return mShoot;
    }
}

package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MEncountersCommandCompatibility} and its DTO {@link MEncountersCommandCompatibilityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MEncountersCommandCompatibilityMapper extends EntityMapper<MEncountersCommandCompatibilityDTO, MEncountersCommandCompatibility> {



    default MEncountersCommandCompatibility fromId(Long id) {
        if (id == null) {
            return null;
        }
        MEncountersCommandCompatibility mEncountersCommandCompatibility = new MEncountersCommandCompatibility();
        mEncountersCommandCompatibility.setId(id);
        return mEncountersCommandCompatibility;
    }
}

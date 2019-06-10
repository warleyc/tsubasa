package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MFormationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MFormation} and its DTO {@link MFormationDTO}.
 */
@Mapper(componentModel = "spring", uses = {MPassiveEffectRangeMapper.class})
public interface MFormationMapper extends EntityMapper<MFormationDTO, MFormation> {

    @Mapping(source = "id.id", target = "idId")
    MFormationDTO toDto(MFormation mFormation);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "mNpcDecks", ignore = true)
    @Mapping(target = "mTargetFormationGroups", ignore = true)
    MFormation toEntity(MFormationDTO mFormationDTO);

    default MFormation fromId(Long id) {
        if (id == null) {
            return null;
        }
        MFormation mFormation = new MFormation();
        mFormation.setId(id);
        return mFormation;
    }
}

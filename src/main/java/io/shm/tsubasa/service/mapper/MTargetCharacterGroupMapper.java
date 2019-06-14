package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetCharacterGroup} and its DTO {@link MTargetCharacterGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCharacterMapper.class})
public interface MTargetCharacterGroupMapper extends EntityMapper<MTargetCharacterGroupDTO, MTargetCharacterGroup> {

    @Mapping(source = "mcharacter.id", target = "mcharacterId")
    MTargetCharacterGroupDTO toDto(MTargetCharacterGroup mTargetCharacterGroup);

    @Mapping(source = "mcharacterId", target = "mcharacter")
    MTargetCharacterGroup toEntity(MTargetCharacterGroupDTO mTargetCharacterGroupDTO);

    default MTargetCharacterGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetCharacterGroup mTargetCharacterGroup = new MTargetCharacterGroup();
        mTargetCharacterGroup.setId(id);
        return mTargetCharacterGroup;
    }
}

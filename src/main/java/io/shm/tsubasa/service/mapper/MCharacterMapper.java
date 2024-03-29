package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCharacterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCharacter} and its DTO {@link MCharacterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCharacterMapper extends EntityMapper<MCharacterDTO, MCharacter> {


    @Mapping(target = "mActionSkillHolderCardContents", ignore = true)
    @Mapping(target = "mCombinationCutPositions", ignore = true)
    @Mapping(target = "mMatchResultCutins", ignore = true)
    @Mapping(target = "mNpcCards", ignore = true)
    @Mapping(target = "mTargetCharacterGroups", ignore = true)
    MCharacter toEntity(MCharacterDTO mCharacterDTO);

    default MCharacter fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCharacter mCharacter = new MCharacter();
        mCharacter.setId(id);
        return mCharacter;
    }
}

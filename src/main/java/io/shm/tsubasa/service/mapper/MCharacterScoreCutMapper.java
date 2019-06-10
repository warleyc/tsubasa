package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCharacterScoreCutDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCharacterScoreCut} and its DTO {@link MCharacterScoreCutDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCharacterScoreCutMapper extends EntityMapper<MCharacterScoreCutDTO, MCharacterScoreCut> {



    default MCharacterScoreCut fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCharacterScoreCut mCharacterScoreCut = new MCharacterScoreCut();
        mCharacterScoreCut.setId(id);
        return mCharacterScoreCut;
    }
}

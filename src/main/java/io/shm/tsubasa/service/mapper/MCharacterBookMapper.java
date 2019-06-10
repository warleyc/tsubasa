package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCharacterBookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCharacterBook} and its DTO {@link MCharacterBookDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCharacterBookMapper extends EntityMapper<MCharacterBookDTO, MCharacterBook> {



    default MCharacterBook fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCharacterBook mCharacterBook = new MCharacterBook();
        mCharacterBook.setId(id);
        return mCharacterBook;
    }
}

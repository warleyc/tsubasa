package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MNgWordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MNgWord} and its DTO {@link MNgWordDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MNgWordMapper extends EntityMapper<MNgWordDTO, MNgWord> {



    default MNgWord fromId(Long id) {
        if (id == null) {
            return null;
        }
        MNgWord mNgWord = new MNgWord();
        mNgWord.setId(id);
        return mNgWord;
    }
}

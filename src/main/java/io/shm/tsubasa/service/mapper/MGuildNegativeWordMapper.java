package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGuildNegativeWordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGuildNegativeWord} and its DTO {@link MGuildNegativeWordDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGuildNegativeWordMapper extends EntityMapper<MGuildNegativeWordDTO, MGuildNegativeWord> {



    default MGuildNegativeWord fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGuildNegativeWord mGuildNegativeWord = new MGuildNegativeWord();
        mGuildNegativeWord.setId(id);
        return mGuildNegativeWord;
    }
}

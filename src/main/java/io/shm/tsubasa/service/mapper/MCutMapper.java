package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCutDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCut} and its DTO {@link MCutDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCutMapper extends EntityMapper<MCutDTO, MCut> {



    default MCut fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCut mCut = new MCut();
        mCut.setId(id);
        return mCut;
    }
}
